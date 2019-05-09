package com.example.demo.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NeoProperties {

    @Value("${com.neo.title}")
    private String title;

    @Value("${com.neo.description}")
    private String description;
}
