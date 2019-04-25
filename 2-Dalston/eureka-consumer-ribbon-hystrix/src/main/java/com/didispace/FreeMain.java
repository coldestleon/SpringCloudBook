package com.didispace;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FreeMain {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.parse("2019-02-14T12:00:04", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
