package com.tracer.logger.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "rest")
public class RestLog {

    @Id
    private String id;
    private String dateInit;
    private String service;
    private Request request;
    private Response response;



    public RestLog(String id, Request request, Response response, String service) {
        this.dateInit = new Date().toString();
        this.request = request;
        this.response = response;
        this.service = service;
        this.id = id;
    }


    public String getId() {
        return id;
    }

    public String getDateInit() {
        return dateInit;
    }

    public void setDateInit(String dateInit) {
        this.dateInit = dateInit;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestLog RestLog)) return false;
        return Objects.equals(id, RestLog.id) && Objects.equals(dateInit, RestLog.dateInit) && Objects.equals(request, RestLog.request) && Objects.equals(response, RestLog.response) && Objects.equals(service, RestLog.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateInit, request, response, service);
    }

    @Override
    public String toString() {
        return "TBLRest{" +
                "id='" + id + '\'' +
                ", dateInit=" + dateInit +
                ", request=" + request +
                ", response=" + response +
                ", service='" + service + '\'' +
                '}';
    }
}
