package me.ofnullable.file.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
@ConfigurationProperties(prefix = "sharebook.ftp")
public class FtpProperties {

    @NotNull
    private Integer port;
    @NotBlank
    private String host;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String basePath;

}
