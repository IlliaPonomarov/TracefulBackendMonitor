package com.tracer.logger.rest.services;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
public class DateService {

    public DateService() {
    }

    public Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Date convertStringToDate(String date) {
        return Date.from(LocalDate.parse(date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date);
    }

}
