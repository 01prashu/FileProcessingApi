package com.file.test.util;

import com.file.test.exception.DocumentProcessingException;
import com.file.test.model.CustomFile;
import com.file.test.repository.FileRepository;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class FileServiceHelper {
    @Autowired
    private FileRepository fileRepo;
    @Value("${app.pdfmerge.path}")
    public String pdfMergePath;
    public  String getFileExtention(String fileName)
    {
        if(fileName == null || !fileName.contains("."))
        {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }
    public  String processMerging(List<MultipartFile> files) throws IOException {
        if(files.isEmpty() || files.size()==1)
        {
            throw new DocumentProcessingException("At least two files are needed to merge");
        }
        PDFMergerUtility merger = new PDFMergerUtility();
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String mergeFileName="Merge_"+timestamp+".pdf";
        for(MultipartFile file:files)
        {
            CustomFile customFile = new CustomFile();
            String fileName=file.getOriginalFilename();
            customFile.setExt(getFileExtention(fileName));
            customFile.setSize(file.getSize());
            customFile.setName((fileName).substring(0, fileName.lastIndexOf(".")));
            customFile.setInsertTime(LocalDateTime.now());
            fileRepo.save(customFile);
            File tempFile=File.createTempFile("upload_", ".pdf");
            file.transferTo(tempFile);
            merger.addSource(tempFile);
            /* tempFile.delete(); */
        }

        File fullPath= new File(pdfMergePath,mergeFileName);
        merger.setDestinationFileName(fullPath.getAbsolutePath());
        merger.mergeDocuments(MemoryUsageSetting.setupTempFileOnly().streamCache);
        return mergeFileName;
    }


    public File fetchFile(String fileName) {

        return new File(pdfMergePath,fileName);
    }
}
