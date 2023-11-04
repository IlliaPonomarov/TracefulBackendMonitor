package com.tracer.logger.json.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracer.logger.kafka.consumers.KafkaLogRecord;
import com.tracer.logger.kafka.dtos.KafkaLogRecordDTO;
import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.RestLog;
import org.apache.kafka.common.protocol.types.Field;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class JSONLogParser {

    public static List<RestLog> parseREST(String pathToJson) {
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        List<RestLog> restLogs = new ArrayList<>();

        try {
            json = objectMapper.readTree(new File(pathToJson)).toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("restLogs");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject restLog = jsonArray.getJSONObject(i);
                String id = restLog.getString("id");
                String service = restLog.getString("service");
                JSONObject requestJSON = restLog.getJSONObject("request");
                JSONObject responseJSON = restLog.getJSONObject("response");

                Request request = new Request(
                        HttpMethod.valueOf(requestJSON.getString("method")),
                        requestJSON.getString("url"),
                        requestJSON.getString("body"),
                        requestJSON.getString("headers")
                );

                Response response = new Response(
                        HttpStatus.valueOf(responseJSON.getInt("statusCode")),
                        responseJSON.getString("headers"),
                        responseJSON.getString("body"),
                        responseJSON.getString("error")
                );
                RestLog tbmRestLogDTO = new RestLog(id, request, response, service);
                tbmRestLogDTO.setDateInit(new Date().toString());
                restLogs.add(tbmRestLogDTO);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return restLogs;
    }


    public static List<KafkaLogRecordDTO> parseKafka(String pathToJson) {
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        List<KafkaLogRecordDTO> kafkaLogRecordDTOS = new ArrayList<>();
        try {
            json = objectMapper.readTree(new File(pathToJson)).toString();
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("kafkaLogRecords");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject kafkaLogRecord = jsonArray.getJSONObject(i);
                String id = kafkaLogRecord.getString("id");
                String message = kafkaLogRecord.getString("message");
                String topic = kafkaLogRecord.getString("topic");
                String key = kafkaLogRecord.getString("key");
                String value = kafkaLogRecord.getString("value");
                int partition = kafkaLogRecord.getInt("partition");
                long offset = kafkaLogRecord.getLong("offset");
                String timestamp = kafkaLogRecord.getString("timestamp");
                String timestampType = kafkaLogRecord.getString("timestampType");
                int serializedKeySize = kafkaLogRecord.getInt("serializedKeySize");
                int serializedValueSize = kafkaLogRecord.getInt("serializedValueSize");
                String headers = kafkaLogRecord.getString("headers");
                String leaderEpoch = kafkaLogRecord.getString("leaderEpoch");

                KafkaLogRecordDTO kafkaLogRecordDTO = new KafkaLogRecordDTO(
                        UUID.fromString(id),
                        message,
                        topic,
                        key,
                        value,
                        partition,
                        offset,
                        timestamp,
                        timestampType,
                        serializedKeySize,
                        serializedValueSize,
                        headers,
                        leaderEpoch
                );
                kafkaLogRecordDTOS.add(kafkaLogRecordDTO);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return kafkaLogRecordDTOS;
    }


    public static <T> String stringify(T dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(dto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String stringify(List<?> recordsDTOS) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(recordsDTOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }





}
