package me.ofnullable.sharebook.config;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;

@Configuration
@PropertySource("classpath:properties/ftp.properties")
public class FtpConfig {

    @Value("${ftp.host}")
    private String ftpHost;
    @Value("${ftp.username}")
    private String ftpUsername;
    @Value("${ftp.password}")
    private String ftpPassword;

    @Bean
    public SessionFactory<FTPFile> sessionFactory() {
        var sf = new DefaultFtpSessionFactory();
        sf.setHost(ftpHost);
        sf.setPort(FTPClient.DEFAULT_PORT);
        sf.setControlEncoding("UTF-8");
        sf.setUsername(ftpUsername);
        sf.setPassword(ftpPassword);
        sf.setDefaultTimeout(5000);
        sf.setConnectTimeout(5000);
        sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
        return new CachingSessionFactory<>(sf, 10);
    }

    /*
    @Bean
    public MessageChannel toFtpChannel() {
        return new DirectChannel();
    }
    @Bean
    public MessageChannel fromFtpChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "toFtpChannel")
    public MessageHandler handler() {
        var handler = new FtpMessageHandler(sessionFactory());
        handler.setCharset("UTF-8");
        handler.setAutoCreateDirectory(true);
        handler.setRemoteDirectoryExpressionString("headers['path']");
        return handler;
    }

    // Can send by gateway with code below too.
    @Bean
    public IntegrationFlow outboundFlow() {
        return IntegrationFlows.from("toFtpChannel")
                .handle(Ftp.outboundAdapter(sessionFactory(), FileExistsMode.REPLACE)
                        .temporaryRemoteDirectory( m -> (String) m.getHeaders().get("path"))
                        .charset("UTF-8")
                        .autoCreateDirectory(true)
                        .remoteDirectoryExpression("headers['path']")
                ).get();
    }
    @Bean
    @ServiceActivator(inputChannel = "toFtpChannel")
    public FtpOutboundGateway outboundGateway() {
        var gw = new FtpOutboundGateway(sessionFactory(), "ls", "payload");
        gw.setOption(AbstractRemoteFileOutboundGateway.Option.ALL);
        gw.setOutputChannelName("fromFtpChannel");
        return gw;
    }
    */

}