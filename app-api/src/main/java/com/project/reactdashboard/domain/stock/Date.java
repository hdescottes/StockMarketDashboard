package com.project.reactdashboard.domain.stock;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

    public static OffsetDateTime parseToISO(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        return OffsetDateTime.parse(date, formatter);
    }

    public static OffsetDateTime lastWorkingDay() {
        OffsetDateTime today = OffsetDateTime.now();
        OffsetDateTime lastWorkingDay = today;

        if (today.getDayOfWeek() != DayOfWeek.SATURDAY && today.getDayOfWeek() != DayOfWeek.SUNDAY) {
            return lastWorkingDay;
        } else {
            while (lastWorkingDay.getDayOfWeek() != DayOfWeek.FRIDAY) {
                lastWorkingDay = lastWorkingDay.minusDays(1);
            }
        }

        return lastWorkingDay;
    }
}
