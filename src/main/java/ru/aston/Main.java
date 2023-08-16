package ru.aston;

public class Main {
    public static void main(String[] args) {

        CustomArrayList<Object> objectCustomArrayList = new CustomArrayList<>();
        for (int i = 0; i < 1000; i++) {
            objectCustomArrayList.add(0, new Object());
        }
    }
}