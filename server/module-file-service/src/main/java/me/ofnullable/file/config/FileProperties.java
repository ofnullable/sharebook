package me.ofnullable.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "sharebook.file")
public class FileProperties {

    private String basePath;
    private FtpProperties ftp;

}
