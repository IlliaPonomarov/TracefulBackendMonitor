package com.tracer.logger.rest.dtos;

import java.util.Objects;

public class ResponseDTO  {

    private String statusCode;
    private String headers;
    private String body;
    private String error;
    private String date;

    public ResponseDTO() {
    }

    public ResponseDTO(String statusCode, String headers, String body, String error, String date) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.body = body;
        this.error = error;
        this.date = date;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResponseDTO that)) return false;
        return Objects.equals(statusCode, that.statusCode) && Objects.equals(headers, that.headers) && Objects.equals(body, that.body) && Objects.equals(error, that.error) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statusCode, headers, body, error, date);

    }


    @Override
    public String toString() {
        return "ResponseDTO{" +
                "statusCode=" + statusCode +
                ", headers='" + headers + '\'' +
                ", body='" + body + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
