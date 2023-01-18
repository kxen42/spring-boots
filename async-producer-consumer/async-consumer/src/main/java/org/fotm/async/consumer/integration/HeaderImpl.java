package org.fotm.async.consumer.integration;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class HeaderImpl implements Header {

    private Map<String, String> headers = new HashMap<>();


    @Override
    public void put(String key, String value) {
        headers.put(key, value);
    }

    @Override
    public String get(String key) {
        return headers.get(key);
    }
}
