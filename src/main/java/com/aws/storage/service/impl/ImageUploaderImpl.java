package com.aws.storage.service.impl;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.aws.storage.entity.AwsFile;
import com.aws.storage.service.AwsFIleOperationService;
import com.aws.storage.service.FileService;
import com.aws.storage.service.ImageUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ImageUploaderImpl implements ImageUploader {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private FileService fileService;

    @Autowired
    private AwsFIleOperationService awsFIleOperationService;

    @Value("${app.s3.bucket}")
    private String bucketName;

    private static final String filePrefix = "VIOS";

    Logger log = LoggerFactory.getLogger(ImageUploaderImpl.class);

    @Override
    public String uploadImage(MultipartFile file) {

        String actualFileName = file.getOriginalFilename();
        if (actualFileName == null){
            System.err.println("invalid filename or fileName is null or empty");
            return "";
        }
//        String fileName = UUID.randomUUID().toString() +
//                actualFileName.substring(actualFileName.lastIndexOf("."));
        String fileName = generateFileName(actualFileName);

//        getting path form filerService for the desire module
        String filePath =  fileService.getCertificateFilePath() + fileName;
        logFileInfoInTable(fileName,bucketName, filePath,"certificate");

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());

        try {
            PutObjectResult putObjectResult =  amazonS3.putObject(
                    new PutObjectRequest(bucketName, filePath, file.getInputStream(), objectMetadata)
            );

        } catch (IOException e) {
//            throw new ImageUploadException(e.toString());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return generatePresignedUrl(fileName);
    }

    private boolean logFileInfoInTable(String fileName, String bucketName, String filePath, String moduleName){
        AwsFile awsFile = new AwsFile();
        awsFile.setFileName(fileName);
        awsFile.setBucketName(bucketName);
        awsFile.setFilePath(filePath);
        awsFile.setModule(moduleName);

        try {
            if (awsFIleOperationService.saveAwsFile(awsFile) != null){
                log.info("file information saved in DB "+ awsFile);
                return true;
            }else{
                log.error("file information is not saved in DB ");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String generateFileName(String actualFileName){
        String suffix = UUID.randomUUID().toString() +
                actualFileName.substring(actualFileName.lastIndexOf("."));
        String fileName  = filePrefix +"_" + suffix;
        return fileName;
    }

    @Override
    public List<String> getAllFiles() {
        try{
            ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(
                    new ListObjectsV2Request().withBucketName(bucketName)
            );
            List<S3ObjectSummary> objectSummaries = listObjectsV2Result.getObjectSummaries();
            List<String> preSignedUrlList = objectSummaries.stream().map(iteam -> generatePresignedUrl(iteam.getKey())).toList();
            return preSignedUrlList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public String generatePresignedUrl(String fileName) {
        Date expireDate = new Date();

//        create url expiration time of 1 hours
        long time = expireDate.getTime();
        int hrs = 1;
        time = time + hrs * 60 * 60  * 1000;
        expireDate.setTime(time);

        try{
            GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, fileName)
                    .withMethod(HttpMethod.GET)
                    .withExpiration(expireDate);

            URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
            return url.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
