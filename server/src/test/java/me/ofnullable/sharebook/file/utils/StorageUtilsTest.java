package me.ofnullable.sharebook.file.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageUtilsTest {

    @Test
    @DisplayName("현재 날짜로 폴더명 생성")
    void makeDirectoryName() {
        var directory = StorageUtils.makeDirectoryName();

        var format = DateTimeFormatter.ofPattern("/YYYYMMdd");
        assertEquals(directory, LocalDate.now().format(format));
    }

    @Test
    @DisplayName("현재 시간과 파일명으로 고유한 파일명 생성")
    void makeUniqueFilename() {
        var filename = StorageUtils.makeUniqueFilename("test.jpg");

        var format = DateTimeFormatter.ofPattern("/HHmm");
        assertTrue(filename.startsWith(LocalDateTime.now().format(format)));
        assertTrue(filename.endsWith("test.jpg"));
    }

}
