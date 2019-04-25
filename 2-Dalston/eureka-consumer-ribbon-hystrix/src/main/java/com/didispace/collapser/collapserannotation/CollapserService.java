package com.didispace.collapser.collapserannotation;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CollapserService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCollapser(batchMethod = "findAll",
    collapserProperties = {@HystrixProperty(name="timerDelayInMilliseconds", value="100")})
    public String find(String id) {
        return null;
    }

    @HystrixCommand
    public List<String> findAll(List<String> ids) {
        return restTemplate.getForObject("http://eureka-client/users?ids={1}", List.class, StringUtils.join(ids, ','));
    }
}
