package com.didispace.collapser;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    public String find(String id) {
        return restTemplate.getForObject("http://eureka-client/user/{1}", String.class, id);
    }
    public List<String> findAll(List<String> ids) {
        return restTemplate.getForObject("http://eureka-client/users?ids={1}", List.class, StringUtils.join(ids, ','));
    }
}
