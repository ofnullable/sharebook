package me.ofnullable.sharebook.utils;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.springframework.util.MimeTypeUtils.IMAGE_JPEG_VALUE;

public class StorageTestUtils {

    private static File getFile() throws FileNotFoundException {
        return ResourceUtils.getFile("classpath:static/image/두근두근-파이썬.jpg");
    }

    public static MockMultipartFile getMultipartFile(String variableName) {
        try {
            var file = getFile();
            return new MockMultipartFile(variableName, file.getName(), IMAGE_JPEG_VALUE, Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new Error(e.getMessage());
        }
    }

}
