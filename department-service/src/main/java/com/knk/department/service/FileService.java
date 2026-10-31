package com.knk.department.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {
	@Value("${file.upload-directory}")
	private String uploadDir;
	
	
	
	public String uploadSingleFile(MultipartFile file) {
		try {
		// TODO Auto-generated method stub
	if(file.isEmpty()) {
		throw new IllegalArgumentException("File is empty");
	}
	
	String fileName=file.getOriginalFilename();
	if(fileName==null || !fileName.matches("(?i)^.+\\.(pdf|png|jpg)")) {
		throw new IllegalArgumentException("file format is wrong");

	}
	
	String contentType=file.getContentType();
	if (contentType == null ||
	        !List.of("application/pdf", "image/png", "image/jpeg")
	                .contains(contentType)) {

	    throw new IllegalArgumentException("Unsupported file type");
	}
	
	
	log.info("############ uploadSingleFile start ######################");
	Path uploadPath= Paths.get(uploadDir).
			toAbsolutePath().normalize().resolve(UUID.randomUUID()+ "_"+file.getOriginalFilename());
	Files.createDirectories(uploadPath);
	Files.copy(file.getInputStream(), uploadPath, StandardCopyOption.REPLACE_EXISTING);

	log.info("############ uploadSingleFile end ######################");

		return "file received "+file.getOriginalFilename();
	} catch (IOException e) {
		// TODO: handle exception
		 throw new RuntimeException("Could not store the file", e);
	}
	
	
		
	}



	public Resource DownloadSingleFile(String fileName) {
		// TODO Auto-generated method stub
		
		try {
			Path downloadPath=Paths.get(uploadDir).toAbsolutePath().normalize().resolve(fileName);
			Resource res= new UrlResource(downloadPath.toUri());
			return res;
		} catch (IOException e) {
			// TODO: handle exception
			 throw new RuntimeException("Could not read the file", e); 
		}
	}

}
