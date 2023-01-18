package com.example.demo;

import com.example.demo.integration.Message;
import com.example.demo.integration.MessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.client.AsyncRestTemplate;

@Slf4j
@SpringBootApplication
public class DemoApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

        String[] responses = new String[3];

        Message message = MessageFactory.createMessage("something");


        new AsyncRestTemplate().getForEntity("http://localhost:8089/api" + message.service1(), String.class)
                               .addCallback(x -> {
                                   log.info("Done: service1");
                                   responses[0] = x.getBody();
                               }, err -> log.error(err.toString()));

        new AsyncRestTemplate().getForEntity("http://localhost:8089/api" + message.service2(), String.class)
                               .addCallback(x -> {
                                   log.info("Done: service2");
                                   responses[1] = x.getBody();
                               }, err -> log.error(err.toString()));

        new AsyncRestTemplate().getForEntity("http://localhost:8089/api" + message.service3(), String.class)
                               .addCallback(x -> {
                                   log.info("Done: service3");
                                   responses[2] = x.getBody();
                               }, err -> log.error(err.toString()));


        Integer limit = 60 * 1000;
        Integer expendedTime = 0;
        log.info("waiting 10 milliseconds");
        while (responses[0] == null || responses[1] == null || responses[2] == null) {
            // busy wait
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.info("wait another 10 milliseconds");
                expendedTime += 10;
                if (expendedTime >= limit) {
                    break;
                }
            }
        }


        log.info("Result -> {}", String.join(" ", responses));

        SpringApplication.exit(ctx, () -> 0);

        /*
        The '() -> 0' is some Java lambda magic to replace an anonymous class

        SpringApplication.exit(ctx, new ExitCodeGenerator() {
            @Override
            public int getExitCode() {
                return 0;
            }
        });
         */

    }

}
