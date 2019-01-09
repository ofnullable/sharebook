package org.slam.web;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.service.common.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileController {
	
	private final FileService fileService;
	
	@PostMapping("/image")
	public String saveImage(MultipartFile bookImage) {
		log.info("FILE NAME : " + bookImage.getOriginalFilename());
		fileService.send(bookImage);
		return "success";
	}
	
	@PostMapping("/images")
	public String saveImages(MultipartFile[] bookImages) {
		Arrays.stream(bookImages).forEach(
				f -> log.info("FILE NAME : " + f.getOriginalFilename())
		);
		fileService.sendAll(bookImages);
		return "success";
	}
	
}
