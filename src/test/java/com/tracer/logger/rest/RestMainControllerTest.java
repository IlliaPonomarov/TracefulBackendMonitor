package com.tracer.logger.rest;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.tracer.logger.json.parser.JSONLogParser;
import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.mappers.RestLogMapper;
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
public class RestMainControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestService restService;


    private List<RestLog> restLogs;


    @BeforeEach
    void setUp() {
        this.restLogs = JSONLogParser.parseREST("src/test/java/com/tracer/logger/assets/restLogs.json");
        System.out.println(restLogs.toString());

        when(restService.findAll()).thenReturn(restLogs);
    }

    @Test
    public void logShouldReturnCreated() throws Exception {
        RestLog restLog = this.restLogs.get(0);
        RestLogDTO restLogDTO = RestLogMapper.convertToDTO(restLog);

        when(restService.log(restLogDTO)).thenReturn(restLog);

        this.mockMvc.perform(post("/api/v1/rest/log")
                .contentType("application/json")
                .content(JSONLogParser.stringify(restLogDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(JSONLogParser.stringify(restLogDTO)));
    }

    @Test
    public void logShouldReturnBadRequest() throws Exception {
        RestLog restLog = this.restLogs.get(0);

        RestLogDTO restLogDTO = RestLogMapper.convertToDTO(restLog);
        restLogDTO.setService(null);

        when(restService.log(restLogDTO)).thenReturn(restLog);

        this.mockMvc.perform(post("/api/v1/rest/log")
                .contentType("application/json")
                .content(JSONLogParser.stringify(restLogDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest());
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
    public void findByUUIDShouldReturnRestLogDTO() throws Exception {
        RestLog restLog = this.restLogs.get(0);
        RestLogDTO restLogDTO = RestLogMapper.convertToDTO(restLog);
        String uuid = restLog.getId();

        when(restService.findById(uuid)).thenReturn(Optional.of(restLog));

        this.mockMvc.perform(get("/api/v1/rest/" + uuid))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().json(JSONLogParser.stringify(restLogDTO)));
    }

    @Test
    public void findByUUIDShouldReturnNotFound() throws Exception {
        String uuid = "550e8400-e29b-41d4-a716-446655440010";

        when(restService.findById(uuid)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/v1/rest/" + uuid))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByUUIDShouldReturnNotFounded() throws Exception {
        String uuid = "550e8400-e29b-41d4-a716-4466554400108";

        when(restService.findById(uuid)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/v1/rest/" + uuid))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteByServiceShouldReturnNoContent() throws Exception {
        String service = this.restLogs.get(0).getService();
        List<RestLog> filteredByService = this.restLogs.stream().filter(restLog -> restLog.getService().equals(service)).toList();

        when(restService.findByService(service)).thenReturn(Optional.of(filteredByService));
        when(restService.deleteByService(filteredByService)).thenReturn(filteredByService);

        List<RestLogDTO> restLogDTOList = filteredByService.stream().map(RestLogMapper::convertToDTO).toList();
        String json = JSONLogParser.stringify(restLogDTOList);

        this.mockMvc.perform(delete("/api/v1/rest/service/" + service))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().json(json));
    }

    @Test
    public void deleteByServiceShouldReturnNotFound() throws Exception {
        String service = "service-not-found";

        when(restService.findByService(service)).thenReturn(Optional.empty());

        this.mockMvc.perform(delete("/api/v1/rest/service/" + service))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void deleteAllShouldReturn200() throws Exception {
        this.mockMvc.perform(delete("/api/v1/rest/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("All logs deleted"));
    }
}
