package com.didispace.annotationcommand;

import com.didispace.annotationcommand.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyAnnotationController {
    @Autowired
    MyService service;

    @GetMapping("service")
    public String service() {
        return service.service();
    }

    @GetMapping("service2")
    public String service2() {
        return service.anotherService();
    }
}
