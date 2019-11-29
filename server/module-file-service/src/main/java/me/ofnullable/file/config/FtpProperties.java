package me.ofnullable.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "sharebook.ftp")
public class FtpProperties {

    private int port;
    private String host;
    private String username;
    private String password;
    private String basePath;

}
