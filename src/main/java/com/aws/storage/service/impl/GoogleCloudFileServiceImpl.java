package com.aws.storage.service.impl;

import com.amazonaws.services.s3.model.Bucket;
import com.aws.storage.service.AwsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoogleCloudFileServiceImpl implements AwsService {

    @Override
    public List<Bucket> getBucketList() {
        return List.of();
    }

    @Override
    public boolean validBucketName(String bucketName) {
        return false;
    }

    @Override
    public void getObjectFromBucket(String bucketName, String objectName) {

    }

    @Override
    public void createBucket(String bucketName) {

    }

    @Override
    public boolean isFileExists(String file) {
        return false;
    }
}
