package com.tracer.logger.kafka;

import com.tracer.logger.json.parser.JSONLogParser;
import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import com.tracer.logger.kafka.dtos.KafkaLogRecordDTO;
import com.tracer.logger.kafka.mappers.KafkaMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.tracer.logger.kafka.services.KafkaLogConsumerService;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class KafkaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KafkaLogConsumerService kafkaLogConsumerService;

    private List<KafkaLogRecordDTO> kafkaLogsDTOS;
    private List<KafkaLogRecord> kafkaLogs;

    @BeforeEach
    void setUp() {
        this.kafkaLogsDTOS = JSONLogParser.parseKafka("src/test/java/com/tracer/logger/assets/kafkaLogs.json");
        this.kafkaLogs = kafkaLogsDTOS.stream().map(KafkaMapper::convertToEntity).toList();
        System.out.println(kafkaLogs.toString());

        when(kafkaLogConsumerService.findAll()).thenReturn(kafkaLogs);
    }

    @Test
    public void getAllShouldReturnOK() throws Exception {
       this.mockMvc.perform(get("/api/v1/kafka/"))
                .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().json(JSONLogParser.stringify(kafkaLogsDTOS)));
    }

    @Test
    public void getAllShouldReturnNotFound() throws Exception {
        when(kafkaLogConsumerService.findAll()).thenReturn(List.of());
        this.mockMvc.perform(get("/api/v1/kafka/"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


}
