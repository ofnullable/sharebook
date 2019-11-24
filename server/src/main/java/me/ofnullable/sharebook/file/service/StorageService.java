package me.ofnullable.sharebook.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {

    String store(MultipartFile image) throws IOException;

}
