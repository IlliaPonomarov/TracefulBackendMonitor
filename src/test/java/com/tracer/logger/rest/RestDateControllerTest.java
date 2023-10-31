package com.tracer.logger.rest;

import com.tracer.logger.jsonParsers.JSONRestLogParser;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RestDateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestService restService;

    private List<RestLog> restLogs;

    @BeforeEach
    void setUp() {
        this.restLogs = JSONRestLogParser.parse("src/test/java/com/tracer/logger/assets/restLogs.json");
    }

    /*
       findByBetweenDateAndService
     */
    @Test
    public void findByBetweenDateAndServiceShouldReturnListOfRestLogDTO() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();
        String end = this.restLogs.get(1).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));
        List<RestLogDTO> expected = restLogs.stream().map(RestLogMapper::convertToDTO).toList();

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByBetweenDateAndService(start, end, service)).thenReturn(restLogs);

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/%s", start, end)).param("service", service))
                .andExpect(status().isFound())
                .andExpect(content().json(JSONRestLogParser.stringify(expected)));

    }

    @Test
    public void findByBetweenDateAndServiceShouldReturnNotFoundService() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();
        String end = this.restLogs.get(1).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));

        when(restService.findByService(service)).thenReturn(Optional.empty());

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/%s", start, end)).param("service", service))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByBetweenDateAndServiceShouldReturnNotFoundServiceBetweenDates() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();
        String end = this.restLogs.get(1).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByBetweenDateAndService(start, end, service)).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get(
                        String.format("/api/v1/rest/date/%s/%s", start, end)).param("service", service))
                .andExpect(status().isNotFound());
    }

    /*
       findByStartDateAndService
     */
    @Test
    public void findByStartDateAndServiceShouldReturnListOfRestLogsDTOS() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));
        List<RestLogDTO> expected = restLogs.stream().map(RestLogMapper::convertToDTO).toList();

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByStartDateAndService(start, service)).thenReturn(restLogs);

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/start", start)).param("service", service))
                .andExpect(status().isFound())
                .andExpect(content().json(JSONRestLogParser.stringify(expected)));
    }


    @Test
    public void findByStartDateAndServiceShouldReturnNotFoundService() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();

        when(restService.findByService(service)).thenReturn(Optional.empty());

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/start", start)).param("service", service))
                .andExpect(status().isNotFound());
    }


    @Test
    public void findByStartDateAndServiceShouldReturnNotFoundServiceByDate() throws Exception {
        String service = this.restLogs.get(0).getService();
        String start = this.restLogs.get(0).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByStartDateAndService(start, service)).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/start", start)).param("service", service))
                .andExpect(status().isNotFound());
    }


    /*
       findByEndDateAndService
     */

    @Test
    public void findByEndDateAndServiceShouldReturnListOfRestLogsDTOS() throws Exception {
        String service = this.restLogs.get(0).getService();
        String end = this.restLogs.get(0).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));
        List<RestLogDTO> expected = restLogs.stream().map(RestLogMapper::convertToDTO).toList();

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByEndDateAndService(end, service)).thenReturn(restLogs);

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/end", end)).param("service", service))
                .andExpect(status().isFound())
                .andExpect(content().json(JSONRestLogParser.stringify(expected)));
    }

    @Test
    public void findByEndDateAndServiceShouldReturnNotFoundService() throws Exception {
        String service = this.restLogs.get(0).getService();
        String end = this.restLogs.get(0).getDateInit();

        when(restService.findByService(service)).thenReturn(Optional.empty());

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/end", end)).param("service", service))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findByEndDateAndServiceShouldReturnNotFoundServiceByDate() throws Exception {
        String service = this.restLogs.get(0).getService();
        String end = this.restLogs.get(0).getDateInit();

        List<RestLog> restLogs = List.of(this.restLogs.get(0), this.restLogs.get(1));

        when(restService.findByService(service)).thenReturn(Optional.of(restLogs));
        when(restService.findByEndDateAndService(end, service)).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get(
                String.format("/api/v1/rest/date/%s/end", end)).param("service", service))
                .andExpect(status().isNotFound());
    }





}
