package com.aws.storage.service;

import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public interface AwsService {

//    aws basic file operations
    List<Bucket> getBucketList();
    boolean validBucketName(String bucketName);
    void getObjectFromBucket(String bucketName , String objectName);
    void createBucket(String bucketName);
    boolean isFileExists(String file);
}
