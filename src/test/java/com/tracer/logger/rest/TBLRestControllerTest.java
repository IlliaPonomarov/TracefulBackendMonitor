package com.tracer.logger.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class TBLRestControllerTest {


    private final MockMvc mockMvc;

    @Autowired
    public TBLRestControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
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
}
