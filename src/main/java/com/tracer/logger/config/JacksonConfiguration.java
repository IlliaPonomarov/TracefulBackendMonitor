package com.tracer.logger.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tracer.logger.rest.jsonser.HttpMethodSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;

@Configuration
public class JacksonConfiguration {

    @Bean
    public SimpleModule httpMethodSerializer() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(HttpMethod.class, new HttpMethodSerializer());
        return module;
    }

}
