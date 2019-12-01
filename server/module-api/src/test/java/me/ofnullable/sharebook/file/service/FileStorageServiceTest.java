package me.ofnullable.sharebook.file.service;

import me.ofnullable.file.service.FileSystemStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static me.ofnullable.sharebook.file.utils.StorageUtils.getMultipartFile;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class FileStorageServiceTest {

    @InjectMocks
    private FileSystemStorageService storageService;

    @Test
    void store() throws IOException {
        var path = storageService.store(getMultipartFile(""));

        assertTrue(path.startsWith(String.format("%s%s", "/image/", getDate())));
    }

    private String getDate() {
        var format = DateTimeFormatter.ofPattern("YYYYMMdd");
        return LocalDate.now().format(format);
    }

}
