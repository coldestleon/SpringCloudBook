package com.didispace.collapser.collapserannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollapserController {
    @Autowired
    CollapserService collapserService;

    @GetMapping("/user/{id}")
    public String findById(@PathVariable String id) {
        return collapserService.find(id);
    }
}
