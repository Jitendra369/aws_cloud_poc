package com.aws.storage.controller;

import com.aws.storage.entity.AwsFile;
import com.aws.storage.service.AwsFIleOperationService;
import com.aws.storage.service.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cloud")
public class FileUploadController {

    @Autowired
    private ImageUploader imageUploader;

    @Autowired
    private AwsFIleOperationService awsFIleOperationService;


    @PostMapping("/file/upload")
    public ResponseEntity<?> uploadFile(@RequestParam MultipartFile file) {
        return ResponseEntity.ok(imageUploader.uploadImage(file));
    }

    @GetMapping("/file/all")
    public ResponseEntity<?> getAllFiles() {
        return ResponseEntity.ok(imageUploader.getAllFiles());
    }

    @PostMapping("/fileData")
    public AwsFile uploadFileData(@RequestBody AwsFile awsFile){
        return awsFIleOperationService.saveAwsFile(awsFile);
    }

    @GetMapping("/all")
    public List<AwsFile> getAllAwsFiles(){
        return awsFIleOperationService.getAllFiles();
    }
}
