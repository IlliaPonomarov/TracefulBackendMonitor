package com.tracer.logger.jsonParsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracer.logger.rest.dtos.RestLogDTO;
import com.tracer.logger.rest.models.Request;
import com.tracer.logger.rest.models.Response;
import com.tracer.logger.rest.models.RestLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JSONRestLogParser {

    public static List<RestLog> parse(String pathToJson) {
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

    public static String stringify(RestLogDTO restLogDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(restLogDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String stringify(List<RestLogDTO> restLogDTOS) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(restLogDTOS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }




}
