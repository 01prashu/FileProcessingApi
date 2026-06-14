package com.file.test;

import com.file.test.model.CustomFile;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

@SpringBootTest
class TestApplicationTests {

	@Value("${app.pdfmerge.path}")
	private String pdfMergePath;
	@Test
	void printTime()
	{

		System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd hh:mm:ss")));
	}
	public void fetchFile(String fileName) {
		System.out.println("MergerPath:"+pdfMergePath);
		File file = new File(pdfMergePath,fileName);
		System.out.println("file Path:"+file.getAbsoluteFile());
		System.out.println("file Name:"+file.getName());
		System.out.println("file length:"+file.length());
	}

	@Test
	void contextLoads() {
		fetchFile("Merged.pdf");
	}

}
