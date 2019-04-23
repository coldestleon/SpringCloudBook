package com.didispace.collapser;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.List;

public class UserBatchCommand extends HystrixCommand<List<String>> {
    UserService userService;
    List<String> ids;
    public UserBatchCommand(UserService userService, List<String> ids) {
        super(HystrixCommandGroupKey.Factory.asKey("haha"));
        this.userService = userService;
        this.ids = ids;
    }
    @Override
    protected List<String> run() throws Exception{
        return userService.findAll(ids);
    }
}
