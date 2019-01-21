package org.slam.utils;

import java.time.LocalDateTime;

public class FtpUtils {

    private static final LocalDateTime LOCAL_DATE = LocalDateTime.now();

    /**
     * Make folder name for ftp server
     *
     * @return String - YEAR + MONTH + DAY
     */
    public String makeDirName() {
        return String.format("%04d%02d%02d", LOCAL_DATE.getYear(), LOCAL_DATE.getMonth().getValue(), LOCAL_DATE.getDayOfMonth());
    }

    /**
     * Make filename for ftp server
     *
     * @return String - HOUR + MINUTE + SECOND + MILLISECOND (
     */
    public String makeFilename() {
        return String.format("%02d%02d%02d%s", LOCAL_DATE.getHour(), LOCAL_DATE.getMinute(), LOCAL_DATE.getSecond(), Integer.toString(LOCAL_DATE.getNano()).substring(0, 3));
    }

}