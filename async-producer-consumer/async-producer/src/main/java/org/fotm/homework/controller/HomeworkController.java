package org.fotm.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableAsync
@RestController
@RequestMapping("/api")
public class HomeworkController {


    @GetMapping("/a")
    public ResponseEntity<String> getHello() {
        log.info("GET /a");
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            // ignore
        }
        ResponseEntity<String> response = new ResponseEntity<>("Hello", HttpStatus.OK);
        log.info(response.toString());
        return response;
    }


    @GetMapping("/b")
    public ResponseEntity<String> getWorld() {
        log.info("GET /b");
        try {
            Thread.sleep(3600);
        } catch (InterruptedException e) {
            // ignore
        }
        ResponseEntity<String> response = new ResponseEntity<>("World", HttpStatus.OK);
        log.info(response.toString());
        return response;
    }

    @GetMapping("/c")
    public ResponseEntity<String> getBang() {
        log.info("GET /c");
        ResponseEntity<String> response = new ResponseEntity<>("!", HttpStatus.OK);
        log.info(response.toString());
        return response;
    }
}
