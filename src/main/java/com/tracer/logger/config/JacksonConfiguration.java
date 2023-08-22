package com.tracer.logger.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public SimpleModule httpMethodSerializer() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(org.springframework.http.HttpMethod.class, new com.tracer.logger.rest.jsonser.HttpMethodSerializer());
        return module;
    }
}
