package org.fotm.async.consumer.integration;

import java.util.UUID;

public class MessageFactory {

    public static Message createMessage(String content) {
        Message message = new Message(new HeaderImpl(), new BodyImpl());

        message.addHeader("id", UUID.randomUUID().toString());
        message.addHeader("service1", "/a");
        message.addHeader("service2", "/b");
        message.addHeader("service3", "/c");
        message.addContent(content);
        return message;
    }

}
