package org.slam.service.common;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.slam.utils.FtpUtils.makeDirName;
import static org.slam.utils.FtpUtils.makeFilename;

@Log4j2
@Service
@AllArgsConstructor
public class FileService {
	
	private final SessionFactory<FTPFile> sf;
	private static final String INITIAL_PATH = "share/book/";
	
	public String send(MultipartFile bookImage) {
		return sendImage(bookImage, this.makePath());
	}
	
	public List<String> sendAll(MultipartFile[] bookImages) {
		var remotePath = this.makePath();
		return Arrays.asList(bookImages).parallelStream()
				.map( bookImage -> sendImage(bookImage, remotePath) )
				.collect(Collectors.toList());
	}
	
	private String sendImage(MultipartFile bookImage, StringBuilder remotePath) {
		try (
				var session = sf.getSession();
				var in = bookImage.getInputStream();
				var bin = new BufferedInputStream(in)
		) {
			var remoteFilePath = remotePath.append(bookImage.getOriginalFilename()).toString();
			log.debug("Send file to remote. Path : {}", remoteFilePath);
			session.append(bin, remoteFilePath);
			return remoteFilePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private StringBuilder makePath() {
		var fullPath = new StringBuilder(INITIAL_PATH).append(makeDirName());
		makeRemoteDir(fullPath.toString());
		return fullPath.append("/").append(makeFilename()).append("-");
	}
	
	private void makeRemoteDir(String fullPath) {
		log.debug("Make remote directory before file send");
		var splitPath = fullPath.split("/");
		var temp = new StringBuilder();
		Arrays.stream(splitPath).forEach( p -> {
			try {
				sf.getSession().mkdir( temp.append("/").append(p).toString() );
			} catch (IOException e) {
				// TODO: Do something for fail to mkdir command
				e.printStackTrace();
			}
		});
	}
	
}
