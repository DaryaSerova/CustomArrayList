package ru.aston.util;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TestObject implements Comparable<TestObject>{

    private Long id;

    private String name;

    private LocalDateTime createDate;

    @Override
    public int compareTo(TestObject o) {
        return this.getId().compareTo(o.getId());
    }
}
