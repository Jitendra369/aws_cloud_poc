package com.aws.storage.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "aws_files")
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
public class AwsFile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fileName;
    private String bucketName;
    private String filePath;
    private String module;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime updatedDate;

    public AwsFile() {
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "AwsFiles{" +
                "id='" + id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
