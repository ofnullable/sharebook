package org.slam.config;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.remote.gateway.AbstractRemoteFileOutboundGateway.Option;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.config.FtpOutboundChannelAdapterParser;
import org.springframework.integration.ftp.dsl.Ftp;
import org.springframework.integration.ftp.gateway.FtpOutboundGateway;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
@Configuration
@PropertySource("classpath:/properties/ftp.properties")
public class FtpConfig {
	
	@Value("${ftp.host}")
	private String FTP_HOST;
	@Value("${ftp.username}")
	private String FTP_USERNAME;
	@Value("${ftp.password}")
	private String FTP_PASSWORD;
	
	@Bean
	public SessionFactory<FTPFile> sessionFactory() {
		log.info("Setup for FTP session factory..");
		var sf = new DefaultFtpSessionFactory();
		sf.setHost(FTP_HOST);
		sf.setPort(FTPClient.DEFAULT_PORT);
		sf.setControlEncoding("UTF-8");
		sf.setUsername(FTP_USERNAME);
		sf.setPassword(FTP_PASSWORD);
		sf.setDefaultTimeout(5000);
		sf.setConnectTimeout(5000);
		sf.setBufferSize(10000);
		sf.setClientMode(FTPClient.PASSIVE_LOCAL_DATA_CONNECTION_MODE);
		return new CachingSessionFactory<>(sf, 10);
	}
	
	
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
	/* Can send by gateway with code below too. */
	/*
	@Bean
	@ServiceActivator(inputChannel = "toFtpChannel")
	public FtpOutboundGateway outboundGateway() {
		var gw = new FtpOutboundGateway(sessionFactory(), "ls", "payload");
		gw.setOption(Option.ALL);
		gw.setOutputChannelName("fromFtpChannel");
		return gw;
	}
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
	*/
	
}
