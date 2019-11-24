package me.ofnullable.sharebook.file.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageUtils {

    /**
     * Make directory name
     *
     * @return String of "/" + YEAR + MONTH + DAY
     */
    public static String makeDirectoryName() {
        var format = DateTimeFormatter.ofPattern("/YYYYMMdd");
        return LocalDate.now().format(format);
    }

    /**
     * Make filename prefix
     *
     * @return String of HOUR + MINUTE + SECOND + MILLISECOND
     */
    private static String makeFilenamePrefix() {
        var format = DateTimeFormatter.ofPattern("HHmmssSSS");
        return LocalDateTime.now().format(format);
    }

    /**
     * Make filename with unique prefix
     *
     * @param originalFilename file's original name
     * @return String of {@link #makeFilenamePrefix()} + "-" + original filename
     */
    public static String makeUniqueFilename(String originalFilename) {
        return String.format("/%s-%s", makeFilenamePrefix(), originalFilename.replace(" ", "-"));
    }

}