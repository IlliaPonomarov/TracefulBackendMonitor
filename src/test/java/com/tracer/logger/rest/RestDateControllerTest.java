package com.tracer.logger.rest;

import com.tracer.logger.rest.models.RestLog;
import com.tracer.logger.rest.services.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class RestDateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestService restService;


    private List<RestLog> restLogs;
}
