package com.aws.storage.service.impl;

import com.aws.storage.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Value("${app.path.certificate.filepath}")
    private String certificateFilePath;

    @Override
    public String getCertificateFilePath() {
        return certificateFilePath;
    }
}
