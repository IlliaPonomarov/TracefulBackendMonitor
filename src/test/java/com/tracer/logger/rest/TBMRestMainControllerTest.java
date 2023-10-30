package com.tracer.logger.rest;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tracer.logger.jsonParsers.JSONRestLogParser;
import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.mappers.TBMRestLogMapper;
import com.tracer.logger.rest.models.RestLog;
import com.tracer.logger.rest.services.RestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;


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

    @Test
    public void findByUUIDShouldReturnRestLogDTOByUUID() throws Exception {
        RestLog restLog = this.restLogs.get(0);
        RestLogDTO restLogDTO = TBMRestLogMapper.convertToDTO(restLog);
        String uuid = restLog.getId();

        when(restService.findById(uuid)).thenReturn(Optional.of(restLog));

        this.mockMvc.perform(get("/api/v1/rest/" + uuid))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(JSONRestLogParser.stringify(restLogDTO)));
    }
}
