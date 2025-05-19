package com.aws.storage.repo;

import com.aws.storage.entity.AwsFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AwsFIleRepo extends JpaRepository<AwsFile, String> {

    List<AwsFile> findByModule(String module);
    Optional<AwsFile> findByFilePath(String filePath);
    AwsFile findByFileName(String fileName);
    List<AwsFile> findByBucketName(String bucketName);
}
