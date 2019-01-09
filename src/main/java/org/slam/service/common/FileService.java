package org.slam.service.common;

import lombok.AllArgsConstructor;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FileService {
	
	private final SessionFactory<FTPFile> sf;
	
	public String send(MultipartFile bookImage) {
		return sendImage(bookImage);
	}
	
	public List<String> sendAll(MultipartFile[] bookImages) {
		return Arrays.stream(bookImages).map(this::sendImage).collect(Collectors.toList());
	}
	
	private String sendImage(MultipartFile bookImage) {
		try (
				var session = sf.getSession();
				var in = bookImage.getInputStream();
				var bin = new BufferedInputStream(in)
		) {
			session.append(bin, "some-where/filename");
			return "some-where/filename";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
