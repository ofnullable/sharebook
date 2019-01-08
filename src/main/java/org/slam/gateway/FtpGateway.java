package org.slam.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

import java.io.File;

@MessagingGateway(defaultRequestChannel = "toFtpChannel", defaultReplyChannel = "fromFtpChannel")
public interface FtpGateway {
	//		List list(String directory);
	void send(File file, @Header("file_name") String filename, @Header("path") String path);
}
