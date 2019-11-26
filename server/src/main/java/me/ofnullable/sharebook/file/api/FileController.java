package me.ofnullable.sharebook.file.api;

import lombok.RequiredArgsConstructor;
import me.ofnullable.sharebook.file.service.StorageService;
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

    private final StorageService storageService;

    @PostMapping("/image")
    public String saveFile(MultipartFile image) throws IOException {
        Assert.notNull(image, "Image file can not be null");
        return storageService.store(image);
    }

}
