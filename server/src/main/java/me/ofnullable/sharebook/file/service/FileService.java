package me.ofnullable.sharebook.file.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static me.ofnullable.sharebook.file.utils.FtpUtils.makeDirName;
import static me.ofnullable.sharebook.file.utils.FtpUtils.makeFilename;

@Service
@RequiredArgsConstructor
public class FileService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final SessionFactory<FTPFile> sf;
    private static final String INITIAL_PATH = "share/book/";

    public String send(MultipartFile bookImage) {
        return sendImage(bookImage, this.makePath());
    }

    public List<String> sendAll(List<MultipartFile> bookImages) {
        var remotePath = this.makePath();
        return bookImages.parallelStream()
                .map(bookImage -> sendImage(bookImage, remotePath))
                .collect(Collectors.toList());
    }

    private String sendImage(MultipartFile bookImage, String remotePath) {
        try (
                var session = sf.getSession();
                var bin = new BufferedInputStream(bookImage.getInputStream())
        ) {
            var remoteFilePath = remotePath + bookImage.getOriginalFilename();
            log.debug("Send file to remote. Path : {}", remoteFilePath);
            session.append(bin, remoteFilePath);
            return remoteFilePath;
        } catch (IOException e) {
            log.error("Fail to send file.. PATH : {}, FILENAME : {}", remotePath, bookImage.getOriginalFilename());
            e.printStackTrace();
        }
        return null;
    }

    private String makePath() {
        var fullPath = new StringBuilder(INITIAL_PATH).append(makeDirName());
        makeRemoteDir(fullPath.toString());
        return fullPath.append("/").append(makeFilename()).append("-").toString();
    }

    private void makeRemoteDir(String fullPath) {
        var splitPath = fullPath.split("/");
        var temp = new StringBuilder();
        Arrays.stream(splitPath).forEach(p -> {
            try (var session = sf.getSession()) {
                log.debug("mkdir : {}", temp);
                if (StringUtils.hasText(p)) {
                    session.mkdir(temp.append("/").append(p).toString());
                }
            } catch (IOException e) {
                log.error("Can not make directory. Path : {}", temp);
                e.printStackTrace();
            }
        });
    }

}
