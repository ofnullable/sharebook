package me.ofnullable.file.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static me.ofnullable.file.utils.StorageUtils.makeDirectoryName;
import static me.ofnullable.file.utils.StorageUtils.makeUniqueFilename;

public class FileSystemStorageService implements FileStorageService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FileSystemStorageService() {
        log.info("Create file system storage service!");
    }

    @Override
    public String store(MultipartFile image) throws IOException {
        return save(image);
    }

    private String save(MultipartFile image) throws IOException {
        var targetFile = makeFile(makeDir(), image.getOriginalFilename());
        image.transferTo(targetFile);

        log.info("file save success at '{}'", targetFile.getPath());
        return "/image/" + targetFile.getParentFile().getName() + "/" + targetFile.getName();
    }

    private File makeFile(File directory, String filename) {
        return new File(directory, makeUniqueFilename(filename));
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
