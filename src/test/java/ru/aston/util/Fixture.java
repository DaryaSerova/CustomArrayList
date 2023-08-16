package ru.aston.util;

import java.time.LocalDateTime;

public class Fixture {

    public static TestObject generateTestObject(Long id, LocalDateTime createDate, Integer year, String name) {
        return TestObject.builder()
                .id(id)
                .createDate(createDate)
                .year(year)
                .name(name)
                .build();
    }

}
