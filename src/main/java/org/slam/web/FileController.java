package org.slam.web;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Log4j2
@RestController
@RequestMapping("/files")
public class FileController {
	
	@PostMapping("/images")
	public String saveImages(MultipartFile[] bookImages) {
		Arrays.stream(bookImages).forEach(
				f -> log.info("FILE NAME : " + f.getOriginalFilename())
		);
		return "success";
	}
	
}
