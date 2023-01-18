package com.example.demo.integration;

public interface Header {

    void put(String key, String value);
    String get(String key);
}
