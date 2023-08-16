package ru.aston.util;

import java.util.Comparator;

public class TestObjectComparator implements Comparator<TestObject> {

    @Override
    public int compare(TestObject o1, TestObject o2) {
        return o1.getYear() - o2.getYear();
    }
}
