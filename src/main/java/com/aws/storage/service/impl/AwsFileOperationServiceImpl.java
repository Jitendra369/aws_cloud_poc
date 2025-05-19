package com.aws.storage.service.impl;

import com.aws.storage.entity.AwsFile;
import com.aws.storage.repo.AwsFIleRepo;
import com.aws.storage.service.AwsFIleOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AwsFileOperationServiceImpl implements AwsFIleOperationService {

    @Autowired
    private AwsFIleRepo awsFIleRepo;

    @Override
    public AwsFile saveAwsFile(AwsFile awsFile) {
        AwsFile saveAwsFileInfo = awsFIleRepo.save(awsFile);
        return saveAwsFileInfo;
    }

    @Override
    public List<AwsFile> getWasFileByModule(String module) {
        return awsFIleRepo.findByModule(module);
    }

    @Override
    public AwsFile findByFilePath(String filePath) {
        return awsFIleRepo.findByFilePath(filePath)
                .orElseThrow(() -> new RuntimeException("File not found for path: " + filePath));
    }

    @Override
    public List<AwsFile> getAllFiles() {
        return awsFIleRepo.findAll();
    }
}
