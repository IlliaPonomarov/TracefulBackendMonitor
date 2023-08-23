package com.tracer.logger.rest.exceptions;

import java.util.Date;
import java.util.Objects;

public class ErrorMessage {
    private final String message;
    private final int code;
    private final Date date;

    public ErrorMessage(String message, int code) {
        this.message = message;
        this.code = code;
        this.date = new Date();
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ErrorMessage)) return false;

        ErrorMessage that = (ErrorMessage) o;

        if (getCode() != that.getCode()) return false;
        if (!getMessage().equals(that.getMessage())) return false;
        return getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.message, this.code, this.date);
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", date=" + date +
                '}';
    }
}
