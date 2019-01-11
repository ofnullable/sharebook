package org.slam.ftp;

import org.apache.commons.net.ftp.FTPFile;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slam.gateway.FtpGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FtpTest {
	
	@Autowired
	private FtpGateway gw;
	@Autowired
	SessionFactory<FTPFile> sf;
	private List<File> fileList;
	
	@Before
	public void init() throws FileNotFoundException {
		File file1 = ResourceUtils.getFile(this.getClass().getResource("/image1.jpg"));
		File file2 = ResourceUtils.getFile(this.getClass().getResource("/image2.jpg"));
		File file3 = ResourceUtils.getFile(this.getClass().getResource("/image3.jpg"));
		File file4 = ResourceUtils.getFile(this.getClass().getResource("/image4.jpg"));
		File file5 = ResourceUtils.getFile(this.getClass().getResource("/image5.jpg"));
		fileList = List.of(file1, file2, file3, file4, file5);
	}
	
	@Test
	public void CLIENT_BIG_FILE_TEST() {
		var file = new File("D:/images/test-image.gif");
		long startTime = System.currentTimeMillis();
		try (
				var session = sf.getSession();
				var in = new FileInputStream(file);
				var bin = new BufferedInputStream(in)
		) {
			session.append( bin, "/spring/integration/c-" + file.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("CLIENT SEND ( BIG FILE ) TIME : " + (endTime - startTime));
	}
	
	@Test
	public void CLIENT_MULTI_FILE_SEND_TEST() {
		long startTime = System.currentTimeMillis();
		fileList.forEach( f -> {
			try (
					var session = sf.getSession();
					var in = new FileInputStream(f);
					var bin = new BufferedInputStream(in)
			) {
				session.append( bin, "/spring/integration/c-" + f.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		long endTime = System.currentTimeMillis();
		System.out.println("CLIENT SEND TIME : " + (endTime - startTime));
	}
	
	@Test
	public void GATEWAY_BIG_FILE_TEST() {
		var file = new File("D:/images/test-image.gif");
		long startTime = System.currentTimeMillis();
		try (
				var in = new FileInputStream(file);
				var bin = new BufferedInputStream(in)
		) {
			gw.send( bin.readAllBytes(), "gw-" + file.getName(), "/spring/integration");
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("GATEWAY SEND ( BIG FILE ) TIME : " + (endTime - startTime));
	}
	
	@Test
	public void GATEWAY_MULTI_FILE_SEND_TEST() {
		long startTime = System.currentTimeMillis();
		fileList.forEach( f -> {
			try (
					var in = new FileInputStream(f);
					var bin = new BufferedInputStream(in)
			) {
				gw.send( bin.readAllBytes(), "gw-" + f.getName(), "/spring/integration");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		long endTime = System.currentTimeMillis();
		System.out.println("GATEWAY SEND TIME : " + (endTime - startTime));
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
				e.printStackTrace();
			}
		});
	}
	
}
