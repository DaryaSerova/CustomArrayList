package ru.aston.util;

import java.time.LocalDateTime;

public class Fixture {

    public static TestObject generateTestObject( Long id, LocalDateTime createDate, String name){
       return TestObject.builder()
                .id(id)
                .createDate(createDate)
               .name(name)
                .build();
    }

}
