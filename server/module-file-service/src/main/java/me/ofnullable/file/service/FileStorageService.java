package me.ofnullable.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorageService {

    String store(MultipartFile image) throws IOException;

}
