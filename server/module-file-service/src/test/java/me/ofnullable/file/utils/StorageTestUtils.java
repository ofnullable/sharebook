package me.ofnullable.file.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static me.ofnullable.file.utils.StorageUtils.makeDirectoryName;
import static me.ofnullable.file.utils.StorageUtils.makeUniqueFilename;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTestUtils {

    @Test
    @DisplayName("현재 날짜로 폴더명 생성")
    void make_directory_name() {
        var directory = makeDirectoryName();

        var format = DateTimeFormatter.ofPattern("/YYYYMMdd");
        assertEquals(directory, LocalDate.now().format(format));
    }

    @Test
    @DisplayName("현재 시간과 파일명으로 고유한 파일명 생성")
    void make_unique_filename() {
        var filename = makeUniqueFilename("test.jpg");

        var format = DateTimeFormatter.ofPattern("/HHmm");
        assertTrue(filename.startsWith(LocalDateTime.now().format(format)));
        assertTrue(filename.endsWith(".jpg"));
    }

    private static File getFile() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:static/image/test.jpg");
    }

    public static InputStream getFileInputStream() {
        try {
            var file = getFile();
            return new FileInputStream(file);
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

}
