package com.bank.loan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.loan.externalclient.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {
    @Bean
    ErrorDecoder errorDecoder(){
        System.out.println("DECODER========================================");
        return new CustomErrorDecoder();
    }
}
