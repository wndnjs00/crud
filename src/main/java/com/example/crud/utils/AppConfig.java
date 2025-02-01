package com.example.crud.utils;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    private final MessageSource messageSource;

    public AppConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //SuccessCode에 MessageSource를 주입하도록 빈 설정을 추가
    @Bean
    public ApplicationRunner configureCodes() {
        return args -> {
            SuccessCode.setMessageSource(messageSource);
            ErrorCode.setMessageSource(messageSource);
        };
    }
}