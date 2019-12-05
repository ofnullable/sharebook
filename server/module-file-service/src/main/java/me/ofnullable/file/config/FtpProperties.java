package me.ofnullable.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "sharebook.file.ftp")
public class FtpProperties {

    private String host;
    private Integer port;
    private String username;
    private String password;

}
