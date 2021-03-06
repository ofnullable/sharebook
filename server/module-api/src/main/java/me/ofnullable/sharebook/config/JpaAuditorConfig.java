package me.ofnullable.sharebook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditorConfig  {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () ->
                Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
