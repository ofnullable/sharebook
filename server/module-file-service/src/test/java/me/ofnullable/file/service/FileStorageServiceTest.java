package me.ofnullable.file.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class FileStorageServiceTest {

    @InjectMocks
    private FileSystemStorageService storageService;

    @Test
    void store() throws IOException {
        var file = ResourceUtils.getFile("classpath:static/image/두근두근-파이썬.jpg");

        var path = storageService.store(
                new MockMultipartFile(
                        file.getName(),
                        file.getName(),
                        "image/jpeg",
                        Files.readAllBytes(file.toPath())
                )
        );

        assertTrue(path.startsWith(String.format("%s%s", "/image/", getDate())));
    }

    private String getDate() {
        var format = DateTimeFormatter.ofPattern("YYYYMMdd");
        return LocalDate.now().format(format);
    }

}
