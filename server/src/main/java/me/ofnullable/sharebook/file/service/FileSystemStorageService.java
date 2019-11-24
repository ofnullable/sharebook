package me.ofnullable.sharebook.file.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static me.ofnullable.sharebook.file.utils.StorageUtils.makeDirectoryName;
import static me.ofnullable.sharebook.file.utils.StorageUtils.makeUniqueFilename;

@Service
public class FileSystemStorageService implements StorageService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String store(MultipartFile image) throws IOException {
        return save(image);
    }

    private String save(MultipartFile image) throws IOException {
        var targetFile = makeFile(image.getOriginalFilename());
        image.transferTo(targetFile);

        log.info("file save success at '{}'", targetFile.getPath());
        return "/image/" + targetFile.getParentFile().getName() + "/" + targetFile.getName();
    }

    private File makeFile(String filename) throws IOException {
        return new File(makeDir(), makeUniqueFilename(filename));
    }

    private File makeDir() throws IOException {
        var file = new File(
                ResourceUtils.getFile("classpath:static/image").getPath() + makeDirectoryName());

        if (!file.exists())
            return Files.createDirectory(file.toPath()).toFile();
        else
            return file;
    }

}
