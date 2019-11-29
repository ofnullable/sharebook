package me.ofnullable.sharebook.file.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.file.service.FileStorageService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping("/image")
    public String saveFile(MultipartFile image) throws IOException {
        Assert.notNull(image, "Image file can not be null");
        return fileStorageService.store(image);
    }

}
