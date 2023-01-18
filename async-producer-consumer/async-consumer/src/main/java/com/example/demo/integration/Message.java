package com.example.demo.integration;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class Message {

    private final Header header;
    private final Body body;

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public void addContent(String content) {
        body.setContent(content);
    }

    public String service1() {
        return header.get("service1");
    }

    public String service2() {
        return header.get("service2");
    }

    public String service3() {
        return header.get("service3");
    }

    public String id() {
        return header.get("id");
    }

    public String content() {
      return body.getContent();
    }
}
