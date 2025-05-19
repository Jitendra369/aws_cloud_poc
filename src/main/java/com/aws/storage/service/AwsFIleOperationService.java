package com.aws.storage.service;

import com.aws.storage.entity.AwsFile;

import java.util.List;

public interface AwsFIleOperationService {

    AwsFile saveAwsFile(AwsFile awsFiles);
    List<AwsFile> getWasFileByModule(String module);
    AwsFile findByFilePath(String filePath);
    List<AwsFile> getAllFiles();

}
