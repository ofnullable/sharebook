package org.slam.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class FtpUtils {

    /**
     * Make folder name for ftp server
     * @return String of YEAR + MONTH + DAY
     */
    public static String makeDirName() {
        var now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return String.format("%04d%02d%02d", now.getYear(), now.getMonth().getValue(), now.getDayOfMonth());
    }

    /**
     * Make filename for ftp server
     * @return String of HOUR + MINUTE + SECOND + MILLISECOND (
     */
    public static String makeFilename() {
        var now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return String.format("%02d%02d%02d%s", now.getHour(), now.getMinute(), now.getSecond(), Integer.toString(now.getNano()).substring(0, 3));
    }

}