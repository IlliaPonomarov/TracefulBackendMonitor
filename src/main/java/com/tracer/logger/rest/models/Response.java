package com.tracer.logger.rest.models;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Response {

    private UUID id;
    private HttpStatus statusCode;

    private String headers;

    private String body;

    private String error;
    private Date date;

    public Response(HttpStatus statusCode, String headers, String body, String error) {
        this.id = UUID.randomUUID();
        this.date = new Date();
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.error = error;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Response response)) return false;
        return Objects.equals(id, response.id) && Objects.equals(statusCode, response.statusCode) && Objects.equals(headers, response.headers) && Objects.equals(body, response.body) && Objects.equals(error, response.error) && Objects.equals(date, response.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, statusCode, headers, body, error, date);
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", statusCode=" + statusCode +
                ", headers='" + headers + '\'' +
                ", body='" + body + '\'' +
                ", error='" + error + '\'' +
                ", date=" + date +
                '}';
    }
}
