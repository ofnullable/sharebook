package org.slam.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "toFtpChannel")
public interface FtpGateway {
    //		List list(String directory);
    void send(byte[] in, @Header("file_name") String filename, @Header("path") String path);
}