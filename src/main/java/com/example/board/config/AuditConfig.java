package com.example.board.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

@EnableJpaAuditing
@Configuration
public class AuditConfig {
	
    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
        //Spring Security의 Authentication을 가져와서 사용자명 반환
    }

}
