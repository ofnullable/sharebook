package org.slam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.slam.mapper")
@SpringBootApplication
public class PublicShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicShareApplication.class, args);
    }

}