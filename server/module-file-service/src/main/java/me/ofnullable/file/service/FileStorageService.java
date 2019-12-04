package me.ofnullable.file.service;

import java.io.IOException;
import java.io.InputStream;

public interface FileStorageService {

    String store(InputStream in, String originalFilename) throws IOException;

}
