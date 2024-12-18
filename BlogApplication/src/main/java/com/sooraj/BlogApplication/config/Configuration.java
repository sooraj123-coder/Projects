package com.sooraj.BlogApplication.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper getMapperObject(){
        return new ModelMapper();
    }
}
