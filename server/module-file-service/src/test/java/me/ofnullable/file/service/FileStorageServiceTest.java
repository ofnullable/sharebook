package me.ofnullable.file.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static me.ofnullable.file.utils.StorageTestUtils.getFileInputStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class FileStorageServiceTest {

    @InjectMocks
    private FileSystemStorageService storageService;

    @Test
    void store() throws IOException {
        var inputStream = getFileInputStream();
        var uri = storageService.store(inputStream, "test.jpg");

        assertTrue(uri.startsWith(String.format("%s%s", "/image/", getDate())));
    }

    private String getDate() {
        var format = DateTimeFormatter.ofPattern("YYYYMMdd");
        return LocalDate.now().format(format);
    }

}
