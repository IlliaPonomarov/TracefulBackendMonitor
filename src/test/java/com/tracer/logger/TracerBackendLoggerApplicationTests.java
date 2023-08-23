package com.tracer.logger;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tracer.logger.rest.controllers.TBLRestMainController;
import com.tracer.logger.rest.jsonser.HttpMethodSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TracerBackendLoggerApplicationTests {

    private final TBLRestMainController tblRestMainController;

    @Autowired
    public TracerBackendLoggerApplicationTests(TBLRestMainController tblRestMainController) {
        this.tblRestMainController = tblRestMainController;
    }
    @Test
    void contextLoads() {
        assertThat(tblRestMainController).isNotNull();
    }

    @Bean
    public SimpleModule httpMethodSerializer() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(HttpMethod.class, new HttpMethodSerializer());
        return module;
    }

}
