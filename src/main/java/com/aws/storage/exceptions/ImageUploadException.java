package com.aws.storage.exceptions;

import org.springframework.stereotype.Component;

//@Component
public class ImageUploadException extends RuntimeException{

    public ImageUploadException(String message){
        System.err.println("exception while upload the image " + message);
    }
}
