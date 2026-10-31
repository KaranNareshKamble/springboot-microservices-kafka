package com.knk.department.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.knk.department.service.FileService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
	
	private final FileService fileService;


	@PostMapping("/singleUpload")
	public ResponseEntity<String> uploadSingleFile(@RequestParam("file") MultipartFile file){
		String res= fileService.uploadSingleFile(file);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(res);
	}
	
	@GetMapping("/singleDownload/{fileName}")
	public ResponseEntity<Resource> downloadSingleFile(@PathVariable("fileName") String fileName){
		Resource res=fileService.DownloadSingleFile(fileName);
		
	    return ResponseEntity.ok()
	            .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
	            .header(
	                    HttpHeaders.CONTENT_DISPOSITION,
	                    ContentDisposition.attachment()
	                            .filename(res.getFilename())
	                            .build()
	                            .toString())
	            .body(res);
	                            
				
	}
}
