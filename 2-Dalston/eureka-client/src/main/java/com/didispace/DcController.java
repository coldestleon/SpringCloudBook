package com.didispace;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 翟永超
 * @create 2017/4/15.
 * @blog http://blog.didispace.com
 */
@RestController
public class DcController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/dc")
    public String dc() {
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable String id) {
        System.out.println(id);
        return "User" + id;
    }

    @GetMapping("users")
    public List<String> getUsers(@RequestParam(value = "ids") List<String> ids) {
        printParam(ids);
        return ids.stream().map(it -> "User" + it).collect(Collectors.toList());
    }


    private void printParam(List<String> id) {
        id.stream().forEach(it -> System.out.println(it + " & "));
    }
}
