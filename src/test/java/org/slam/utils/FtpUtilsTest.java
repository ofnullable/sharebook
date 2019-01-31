package org.slam.utils;

import org.junit.Test;

public class FtpUtilsTest {

	@Test
	public void dirNameTest() {
		System.out.println("Directory name : " + FtpUtils.makeDirName());
	}
	
	@Test
	public void filenameTest() {
		System.out.println("File name : " + FtpUtils.makeFilename());
	}
	
}
