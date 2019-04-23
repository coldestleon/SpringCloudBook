package com.didispace.Command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class MyCommand extends HystrixCommand<String> {
    public MyCommand(HystrixCommandGroupKey key) {
        super(key);
    }
    @Override
    protected String run() throws Exception {
        System.out.println("run by: " + Thread.currentThread().getName());
//        Thread.sleep(10000);
        return "run";
    }

    @Override
    protected String getFallback() throws RuntimeException {
        System.out.println("fallback now");
//        return "fallback";
        throw new RuntimeException("something wrong in fallback method");
    }
}
