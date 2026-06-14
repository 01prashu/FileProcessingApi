package com.file.test.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;


public class DocumentProcessingException extends  RuntimeException{

    public DocumentProcessingException(String msg)
    {
        super(msg);
    }
    public DocumentProcessingException(String msg,Throwable cause)
    {
        super(msg,cause);
    }
}
