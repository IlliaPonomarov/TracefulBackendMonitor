package com.tracer.logger.rest;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tracer.logger.jsonParsers.JSONRestLogParser;
import com.tracer.logger.rest.exceptions.TBMRestLogNotFounded;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.RestLog;
import com.tracer.logger.rest.services.RestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Date;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class TBMRestMainControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestService restService;


    private List<RestLog> restLogs;


    @BeforeEach
    void setUp() {
        this.restLogs = JSONRestLogParser.parse("src/test/java/com/tracer/logger/assets/restLogs.json");
        System.out.println(restLogs.toString());

        when(restService.findAll()).thenReturn(restLogs);
    }


    @Test
    public void shouldFetchAllLogs() throws Exception {
        this.mockMvc.perform(get("/api/v1/rest/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldThrowExceptionWhenLogNotFounded() throws Exception {
        when(restService.findAll()).thenReturn(List.of());
        this.mockMvc.perform(get("/api/v1/rest/1"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
