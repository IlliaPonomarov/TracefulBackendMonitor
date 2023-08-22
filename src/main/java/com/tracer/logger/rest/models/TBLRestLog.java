package com.tracer.logger.rest.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Objects;

@Document(collection = "rest")
public class TBLRestLog {

    @Id
    private String id;
    private Date dateInit;
    private Request request;
    private Response response;



    public TBLRestLog(Request request, Response response) {
        this.dateInit = new Date();
        this.request = request;
        this.response = response;
    }


    public String getId() {
        return id;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TBLRestLog tblRestLog)) return false;
        return Objects.equals(id, tblRestLog.id) && Objects.equals(dateInit, tblRestLog.dateInit) && Objects.equals(request, tblRestLog.request) && Objects.equals(response, tblRestLog.response);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateInit, request, response);
    }

    @Override
    public String toString() {
        return "TBLRest{" +
                "id='" + id + '\'' +
                ", dateInit=" + dateInit +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
