package com.tracer.logger.rest.models;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Request {
    private UUID id;
    private HttpMethod method;
    private String url;

    private String headers;
    private String body;

    private Date date;

    public Request(HttpMethod method, String url, String body, String headers) {
        this.id = UUID.randomUUID();
        this.date = new Date();
        this.method = method;
        this.url = url;
        this.body = body;
        this.headers = headers;
    }

    public UUID getId() {
        return id;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(o instanceof Request request)) return false;
        return Objects.equals(id, request.id) && Objects.equals(method, request.method) && Objects.equals(url, request.url) && Objects.equals(body, request.body) && Objects.equals(date, request.date) && Objects.equals(headers, request.headers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, method, url, body, date, headers);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", method=" + method +
                ", url='" + url + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", headers='" + headers + '\'' +
                '}';
    }
}
