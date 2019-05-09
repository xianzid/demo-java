package com.example.demo.test.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@SpringBootApplication
public class GateWaySampleApplication {
    @Value("${remote.home}")
    private URI home;

    /*@GetMapping("/test")
    public ResponseEntity<?> proxy(ProxyExchange<byte[]> proxy){
        return proxy.uri(home.toString() + "/image/png").get();
    }*/

    public static void main(String[] args) {

    }
}
