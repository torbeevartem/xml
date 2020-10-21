package com.example.xml.exceptions;

public class MapperException extends RuntimeException {
    public MapperException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MapperException(String str) {
    }
}
