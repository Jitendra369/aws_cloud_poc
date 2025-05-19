package com.aws.storage.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageUploader {

    String uploadImage(MultipartFile file);
    List<String> getAllFiles();
    String generatePresignedUrl(String fileName);
}
