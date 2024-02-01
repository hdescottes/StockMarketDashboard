package com.project.reactdashboard.domain.stock;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.OffsetDateTime;

import static com.project.reactdashboard.domain.stock.Date.lastWorkingDay;
import static com.project.reactdashboard.domain.stock.Date.parseToISO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DateTest {

    @Test
    void should_parse_string_date() {
        int year = 2023;
        int month = 12;
        String date = year + "-" + month + "-" + "08T00:00:00+0000";

        OffsetDateTime result = parseToISO(date);

        assertEquals(result.getYear(), year);
        assertEquals(result.getMonth().getValue(), month);
    }

    @Test
    public void should_return_last_working_day() {
        OffsetDateTime lastWorkingDay = lastWorkingDay();

        assertNotEquals(DayOfWeek.SATURDAY, lastWorkingDay.getDayOfWeek());
        assertNotEquals(DayOfWeek.SUNDAY, lastWorkingDay.getDayOfWeek());
    }
}
