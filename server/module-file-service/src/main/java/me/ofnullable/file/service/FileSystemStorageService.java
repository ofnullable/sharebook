package me.ofnullable.file.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.file.Files;

import static me.ofnullable.file.utils.StorageUtils.makeDirectoryName;
import static me.ofnullable.file.utils.StorageUtils.makeUniqueFilename;
import static org.springframework.util.ResourceUtils.CLASSPATH_URL_PREFIX;

public class FileSystemStorageService implements FileStorageService {

    private static final String DEFAULT_PATH = "classpath:static/image";
    private static final String RESOURCES_PREFIX = "classpath:static";

    private final String basePath;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FileSystemStorageService(String basePath) {
        log.info("Create file system storage service!");

        if (StringUtils.hasText(basePath)) {
            this.basePath = RESOURCES_PREFIX + basePath;
        } else {
            this.basePath = DEFAULT_PATH;
        }
    }

    @Override
    public String store(InputStream in, String originalFilename) throws IOException {
        return save(in, originalFilename);
    }

    private String save(InputStream in, String originalFilename) throws IOException {
        var targetFile = makeFile(makeDir(), originalFilename);

        try (var bin = new BufferedInputStream(in)) {
            FileCopyUtils.copy(bin, Files.newOutputStream(targetFile.toPath()));
        }

        log.info("file save success at '{}'", targetFile.getPath());
        return makeResourcePath(targetFile.getAbsolutePath());
    }

    private File makeFile(File directory, String filename) {
        return new File(directory, makeUniqueFilename(filename));
    }

    private File makeDir() throws IOException {
        File dir = getFile(basePath);

        if (dir.exists())
            return dir;
        else
            return Files.createDirectories(dir.toPath()).toFile();
    }

    private File getFile(String basePath) throws FileNotFoundException {
        if (basePath.startsWith(CLASSPATH_URL_PREFIX)) {
            return new File(ResourceUtils.getFile(basePath).getPath(), makeDirectoryName());
        } else {
            return new File(basePath, makeDirectoryName());
        }
    }

    private String makeResourcePath(String absolutePath) throws FileNotFoundException {
        var resourcePath = ResourceUtils.getFile(RESOURCES_PREFIX).getAbsolutePath();
        return absolutePath.substring(resourcePath.length()).replace("\\", "/");
    }

}
