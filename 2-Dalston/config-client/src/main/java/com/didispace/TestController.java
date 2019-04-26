package com.didispace;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class TestController {
    @Value("${from}")
    private String from;

    @Autowired
    Environment env;

    @GetMapping("/from")
    public String from() {
        return this.from;
    }

    @GetMapping("/myhealth")
    public String health() {
        return "myhealth";
    }
    @GetMapping("/envfrom")
    public String envFrom() {
        return env.getProperty("from", "defaultValue");
    }

    @GetMapping("/cannotoverride")
    public String cannotoverride() {
        return env.getProperty("cannotovrride", "defaultValue");
    }
}
