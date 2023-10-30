package com.tracer.logger.rest.dtos;

import com.tracer.logger.rest.models.Request;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class RequestDTO {
    private HttpMethod method;
    private String url;
    private String body;
    private String headers;
    private Date dateInit;

    public RequestDTO(HttpMethod method, String url, String body, String headers, Date dateInit) {
        this.method = method;
        this.url = url;
        this.body = body;
        this.headers = headers;
        this.dateInit = dateInit;
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

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateInit() {
        return dateInit;
    }

    public void setDateInit(Date dateInit) {
        this.dateInit = dateInit;
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
        return Objects.equals(method, that.method) && Objects.equals(url, that.url) && Objects.equals(body, that.body) && Objects.equals(headers, that.headers) && Objects.equals(dateInit, that.dateInit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, url, body, headers, dateInit);
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
