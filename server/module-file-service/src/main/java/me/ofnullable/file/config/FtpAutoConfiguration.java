package me.ofnullable.file.config;

import me.ofnullable.file.service.FileStorageService;
import me.ofnullable.file.service.FileSystemStorageService;
import me.ofnullable.file.service.FtpFileStorageService;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
@EnableConfigurationProperties(FtpProperties.class)
public class FtpAutoConfiguration {

    @Bean(name = "ftpSessionFactory")
    @ConditionalOnProperty("sharebook.ftp.host")
    public SessionFactory<FTPFile> ftpSessionFactory(FtpProperties properties) {
        final int port = properties.getPort() > 0 ? properties.getPort() : FTPClient.DEFAULT_PORT;

        var sf = new DefaultFtpSessionFactory();
        sf.setHost(properties.getHost());
        sf.setPort(port);
        sf.setControlEncoding("UTF-8");
        sf.setUsername(properties.getUsername());
        sf.setPassword(properties.getPassword());
        sf.setDefaultTimeout(5000);
        sf.setConnectTimeout(5000);
        sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
        return new CachingSessionFactory<>(sf, 10);
    }

    @Bean
    @ConditionalOnBean(name = "ftpSessionFactory")
    public FileStorageService ftpFileStorageService(SessionFactory<FTPFile> sf, FtpProperties properties) {
        return new FtpFileStorageService(sf, properties);
    }

    @Bean
    @ConditionalOnMissingBean(name = "ftpSessionFactory")
    public FileStorageService fileSystemStorageService() {
        return new FileSystemStorageService();
    }

}
