package com.file.test.exception;

import com.file.test.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseDto>handleFileNotFoundException(FileNotFoundException ex)
    {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("Failed");
        responseDto.setRemark(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseDto>handleIOException(IOException ex)
    {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("Failed");
        responseDto.setRemark(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
    @ExceptionHandler(DocumentProcessingException.class)
    public ResponseEntity<ResponseDto>handleDocumentProcessException(DocumentProcessingException e)
    {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("Failed");
        responseDto.setStatus(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto>handleGlobalException(Exception e)
    {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("Failed");
        responseDto.setRemark(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
}
