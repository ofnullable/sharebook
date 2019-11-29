package me.ofnullable.file.service;

import me.ofnullable.file.config.FtpProperties;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;

import static me.ofnullable.file.utils.StorageUtils.makeDirectoryName;
import static me.ofnullable.file.utils.StorageUtils.makeUniqueFilename;

public class FtpFileStorageService implements FileStorageService {

    private final SessionFactory<FTPFile> sf;
    private final String basePath;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public FtpFileStorageService(SessionFactory<FTPFile> sf, FtpProperties properties) {
        this.sf = sf;
        this.basePath = properties.getBasePath();
        log.info("Create ftp file storage service!");
    }

    @Override
    public String store(MultipartFile image) throws IOException {
        return transfer(image);
    }

    private String transfer(MultipartFile image) throws IOException {
        try (var session = sf.getSession();
             var bin = new BufferedInputStream(image.getInputStream())) {

            var remoteFilePath = makeRemoteDir() + makeUniqueFilename(image.getOriginalFilename());
            session.append(bin, remoteFilePath);

            log.info("file transfer success at '{}'", remoteFilePath);
            return remoteFilePath;
        }
    }

    private String makeRemoteDir() {
        return createRemoteDirectory(basePath + makeDirectoryName());
    }

    private String createRemoteDirectory(String path) {
        var splitPath = path.split("/");
        var temp = new StringBuilder();

        Arrays.stream(splitPath).forEach(p -> {
            try (var session = sf.getSession()) {
                if (StringUtils.hasText(p)) {
                    var targetPath = temp.append("/").append(p).toString();
                    session.mkdir(targetPath);
                    log.debug("made directory at '{}'", targetPath);
                }
            } catch (IOException e) {
                log.error("can not make directory at '{}'", temp);
                e.printStackTrace();
            }
        });
        return path;
    }

}
