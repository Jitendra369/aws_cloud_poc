package com.aws.storage.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.aws.storage.service.AwsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AwsServiceImpl implements AwsService {

    Logger log = LoggerFactory.getLogger(AwsServiceImpl.class);

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${app.s3.bucket}")
    private String bucketName;

    @Override
    public List<Bucket> getBucketList() {
        log.info("getting bucket information ");
        return amazonS3.listBuckets();
    }

    @Override
    public boolean validBucketName(String bucketName) {
        if (bucketName != null){
            return getBucketList().stream().anyMatch(m -> bucketName.equalsIgnoreCase(m.getName()));
        }
        return false;
    }

    @Override
    public void getObjectFromBucket(String bucketName, String objectName) {

    }

    @Override
    public void createBucket(String bucketName) {
        if (bucketName != null){
            amazonS3.createBucket(bucketName);
        }
    }

    @Override
    public boolean isFileExists(String filePath) {
        return amazonS3.doesObjectExist(bucketName, filePath);
    }
}
