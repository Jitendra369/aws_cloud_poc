package com.aws.storage.controller;

import com.aws.storage.entity.AwsFile;
import com.aws.storage.service.AwsFIleOperationService;
import com.aws.storage.service.AwsService;
import com.aws.storage.service.impl.ImageUploaderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aws")
public class AwsFIleController {

    @Autowired
    @Qualifier("awsServiceImpl")
    private AwsService awsService;

    @Autowired
    private AwsFIleOperationService awsFIleOperationService;

    Logger log = LoggerFactory.getLogger(this.getClass());

//    use to check if file is present
    @PostMapping("/filePresent")
    public ResponseEntity<?> checkFileExists(@RequestBody AwsFile awsFile){

        AwsFile awsFiles = awsFIleOperationService.findByFilePath(awsFile.getFilePath());
        if (awsFiles == null){
            log.info("file not present in database ");
        }else{
            log.info("file location in db "+ awsFile.getFilePath());
        }
        return ResponseEntity.ok().body(awsService.isFileExists(awsFile.getFileName()));
    }
}
