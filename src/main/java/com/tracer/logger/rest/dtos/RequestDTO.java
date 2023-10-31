package com.tracer.logger.rest.dtos;

import java.util.Objects;

public class RequestDTO {
    private String method;
    private String url;
    private String body;
    private String headers;
    private String date;

    public RequestDTO(String method, String url, String body, String headers, String date) {
        this.method = method;
        this.url = url;
        this.body = body;
        this.headers = headers;
        this.date = date;
    }

    public String getMethod() {
        return method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestDTO that)) return false;
        return Objects.equals(method, that.method) && Objects.equals(url, that.url) && Objects.equals(body, that.body) && Objects.equals(headers, that.headers) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url, body, headers, date);
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "method=" + method +
                ", url='" + url + '\'' +
                ", body='" + body + '\'' +
                ", headers='" + headers + '\'' +
                '}';
    }
}
