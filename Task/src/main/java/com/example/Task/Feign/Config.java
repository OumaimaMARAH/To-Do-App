package com.example.Task.Feign;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}
