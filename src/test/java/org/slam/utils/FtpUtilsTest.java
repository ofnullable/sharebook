package org.slam.utils;

import org.junit.Test;

public class FtpUtilsTest {

	private FtpUtils ftpUtils = new FtpUtils();

	@Test
	public void dirNameTest() {
		System.out.println("Directory name : " + ftpUtils.makeDirName());
	}
	
	@Test
	public void filenameTest() {
		System.out.println("File name : " + ftpUtils.makeFilename());
	}
	
}
