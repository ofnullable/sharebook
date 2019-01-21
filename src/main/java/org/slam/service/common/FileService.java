package org.slam.service.common;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.FTPFile;
import org.slam.utils.FtpUtils;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@AllArgsConstructor
public class FileService {

    private final SessionFactory<FTPFile> sf;
    private static final String INITIAL_PATH = "share/book/";
    private final FtpUtils ftpUtils = new FtpUtils();

    public String send(MultipartFile bookImage) {
        return sendImage(bookImage, this.makePath());
    }

    public List<String> sendAll(MultipartFile[] bookImages) {
        var remotePath = this.makePath();
        return Arrays.asList(bookImages).parallelStream()
                .map(bookImage -> sendImage(bookImage, remotePath))
                .collect(Collectors.toList());
    }

    private String sendImage(MultipartFile bookImage, String remotePath) {
        try (
                var session = sf.getSession();
                var in = bookImage.getInputStream();
                var bin = new BufferedInputStream(in)
        ) {
            var remoteFilePath = remotePath + bookImage.getOriginalFilename();
            log.debug("Send file to remote. Path : {}", remoteFilePath);
            session.append(bin, remoteFilePath);
            return remoteFilePath;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String makePath() {
        var fullPath = new StringBuilder(INITIAL_PATH).append(ftpUtils.makeDirName());
        makeRemoteDir(fullPath.toString());
        return fullPath.append("/").append(ftpUtils.makeFilename()).append("-").toString();
    }

    private void makeRemoteDir(String fullPath) {
        var splitPath = fullPath.split("/");
        try ( var session = sf.getSession() ) {
            boolean isExists = session.exists(fullPath);
            log.info("Is exists? Path : {}, result : {}", fullPath, isExists);
            if (!isExists) {
                Arrays.stream(splitPath).forEach( p -> {
                    var temp = new StringBuilder();
                    try {
                        session.mkdir(temp.append("/").append(p).toString());
                    } catch (IOException e) {
                        log.debug("Can not make directory. Path : {}", temp);
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            log.debug("Can not check is exists. Path : {}", fullPath);
            e.printStackTrace();
        }
    }

}