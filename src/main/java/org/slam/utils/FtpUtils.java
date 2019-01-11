package org.slam.utils;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class FtpUtils {

	private static final LocalDateTime LOCAL_DATE = LocalDateTime.now();
	
	/**
	 * Make folder name for ftp server
	 * @return String - YEAR + MONTH + DAY
	 */
	public static String makeDirName() {
		return String.format("%04d%02d%02d", LOCAL_DATE.getYear(), LOCAL_DATE.getMonth().getValue(), LOCAL_DATE.getDayOfMonth());
	}
	
	/**
	 * Make filename for ftp server
	 * @return String - HOUR + MINUTE + SECOND + NANOSECONDS
	 */
	public static String makeFilename() {
		return String.format("%02d%02d%02d%04d", LOCAL_DATE.getHour(), LOCAL_DATE.getMinute(), LOCAL_DATE.getSecond(), LOCAL_DATE.getNano());
	}
	
}
