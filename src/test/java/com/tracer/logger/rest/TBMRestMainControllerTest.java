package com.tracer.logger.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracer.logger.rest.dtos.RequestDTO;
import com.tracer.logger.rest.dtos.ResponseDTO;
import com.tracer.logger.rest.dtos.TBMRestLogDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TBMRestMainControllerTest {


    private final MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @Autowired
    public TBMRestMainControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @BeforeAll
    public static void setup() {
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
    }



    @Test
    public void findAllTest() {
        try {
            this.mockMvc.perform(get("/tbl/rest/"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[*].request").exists())
                    .andExpect(jsonPath("$[*].response").exists());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void logTest(){

        UUID uuid = UUID.randomUUID();
        String header = "header";
        String body = "body";
        String url = "url";
        String httpStatusCode = HttpStatus.OK.toString();
        String errorMessage = "errorMessage";
        HttpMethod method = HttpMethod.GET;

        List<RequestDTO> requestDTO = List.of(new RequestDTO(method, url, body, header, new Date()));
        List<ResponseDTO> responseDTO = List.of(new ResponseDTO(httpStatusCode, errorMessage, body, header, new Date()));

        TBMRestLogDTO TBMRestLogDTO = new TBMRestLogDTO(uuid.toString(), requestDTO, responseDTO, "BusinessService");
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        String json = "";
        try {
            json = objectMapper.writeValueAsString(TBMRestLogDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        json = json.replace("\"method\":{\"name\":\"GET\"}", "\"method\":\"GET\"");
        System.out.println(json);

        try {
            this.mockMvc.perform(post("/tbl/rest/log")
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.request").exists())
                    .andExpect(jsonPath("$.response").exists());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
