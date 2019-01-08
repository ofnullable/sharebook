package org.slam.ftp;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slam.gateway.FtpGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FtpTest {

	@Autowired
	private FtpGateway gw;
	@Autowired
	SessionFactory<FTPFile> sf;
	
	@Test
	public void SEND_FILE_TEST() {
		var file = new File("D:/images/test-image.jpg");
		try {
			gw.send(file, "test.jpg", "/spring/integration");
		} catch (Exception e) {
//			this.makeDirectory("/spring/integration");
//			gw.send(file, "test.jpg", "/spring/integration");
			// TODO: Exception handling: caused by send to NOT exists folder
		}
	}
	
	private void makeDirectory(String path) {
		var splittedPath = path.split("/");
		var temp = new StringBuilder();
		Arrays.stream(splittedPath).forEach( p -> {
			temp.append("/").append(p);
			try {
				sf.getSession().mkdir(temp.toString());
			} catch (IOException e) {
				// TODO: Do something for fail to mkdir command
			}
		});
	}
	
}
