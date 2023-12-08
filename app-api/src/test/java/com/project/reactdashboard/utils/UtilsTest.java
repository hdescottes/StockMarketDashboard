package com.project.reactdashboard.utils;

import org.junit.jupiter.api.Test;

import static com.project.reactdashboard.utils.Utils.splitStockDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilsTest {

    @Test
    void should_split_string_date() {
        String expectedDate = "2023-12-08";
        String date = expectedDate + "T00:00:00+0000";

        String result = splitStockDate(date);

        assertEquals(expectedDate, result);
    }
}
