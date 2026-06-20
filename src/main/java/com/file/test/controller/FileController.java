package com.file.test.controller;

import com.file.test.dto.ResponseDto;
import com.file.test.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    FileService fileService;
    @PostMapping("/merge")
    ResponseEntity<ResponseDto>mergeController
            (@RequestParam("file") List<MultipartFile> file) throws IOException {
        ResponseDto responseDto =fileService.mergeDocument(file);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @GetMapping("/download/{document}")
    ResponseEntity<Object>downloadPdf(@PathVariable String document) throws IOException
    {

        byte[]readAllByte = fileService.downloadPdf(document);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).
                body(readAllByte);
    }
}
