package me.ofnullable.sharebook.file.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

public class StorageUtils {

    @Test
    @DisplayName("현재 날짜로 폴더명 생성")
    void makeDirectoryName() {
        var directory = me.ofnullable.file.utils.StorageUtils.makeDirectoryName();

        var format = DateTimeFormatter.ofPattern("/YYYYMMdd");
        assertEquals(directory, LocalDate.now().format(format));
    }

    @Test
    @DisplayName("현재 시간과 파일명으로 고유한 파일명 생성")
    void makeUniqueFilename() {
        var filename = me.ofnullable.file.utils.StorageUtils.makeUniqueFilename("test.jpg");

        var format = DateTimeFormatter.ofPattern("/HHmm");
        assertTrue(filename.startsWith(LocalDateTime.now().format(format)));
        assertTrue(filename.endsWith("test.jpg"));
    }

    private static File getFile() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:static/image/두근두근-파이썬.jpg");
    }

    public static MockMultipartFile getMultipartFile(String variableName) throws IOException {
        var file = getFile();
        return new MockMultipartFile(variableName, file.getName(), IMAGE_JPEG_VALUE, Files.readAllBytes(file.toPath()));
    }

}
