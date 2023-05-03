package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationFailureHandler customAuthFailureHandler;

    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests()
        		.antMatchers("/admin/**", "/api/users", "/slang-filter").hasRole("ADMIN")
        		.antMatchers("/post", "api/post/**","/api/reply/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")
                .loginProcessingUrl("/auth/signin")
                .defaultSuccessUrl("/")
        		.failureHandler(customAuthFailureHandler);
        return http.build();
    }
	
}


// 블라인드 해지 vs 예외로 두기
// 근데 예외로 둘 일이 뭐가 있지
// 억울한 사람이 관리자한테 문의를 해서 블라인드하는 것일텐데 문의를 해도 관리자 메일로 하겠지?
// 근데 그 사람이 내용 수정을 안한다면 예외처리로 해서 해야될 것이고
// 그렇다면 상태를 예외로 바꾸는 거랑 어... 게시글 삭제기능이 필요하려남
// 내용수정했다고 블라인드 해지해달라고 해도 예외처리로 해두면 해지될 것이고 예외처리 해둔 것도 아마 필터링될테니까 상관없을 듯