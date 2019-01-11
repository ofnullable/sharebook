package org.slam.utils;

import org.junit.Test;

import static org.slam.utils.FtpUtils.makeDirName;
import static org.slam.utils.FtpUtils.makeFilename;

public class FtpUtilsTest {
	
	@Test
	public void dirNameTest() {
		System.out.println("Directory name : " + makeDirName());
	}
	
	@Test
	public void filenameTest() {
		System.out.println("File name : " + makeFilename());
	}
	
}
