package org.slam.publicshare.file.api;

import lombok.RequiredArgsConstructor;
import org.slam.publicshare.file.service.FileService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;

    @PostMapping("/image")
    public String saveFile(@RequestParam MultipartFile image) {
        Assert.notNull(image, "Image file can not be null");
        return fileService.send(image);
    }

    @PostMapping("/images")
    public List<String> saveFiles(@RequestParam List<MultipartFile> images) {
        Assert.notEmpty(images, "Image file can not be null");
        return fileService.sendAll(images);
    }

}
