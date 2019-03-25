package org.slam.web.rest;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slam.service.common.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/files")
@AllArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @PostMapping("/image")
    public String saveImage(MultipartFile bookImage) throws InterruptedException {
        var sendResult = fileService.send(bookImage);
        Thread.sleep( 2 * 1000 ); // Wait for upload to complete
        return sendResult;
    }

    @PostMapping("/images")
    public List<String> saveImages(MultipartFile[] bookImages) throws InterruptedException {
        List<String> sendResults = fileService.sendAll(bookImages);
        Thread.sleep( 2 * 1000 );
        return sendResults;
    }

}