package com.didispace;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class MyService {
    @HystrixCommand(fallbackMethod="handleFallback",
            ignoreExceptions = {CustonException.class},
            commandKey = "commandKey",
            groupKey = "groupKey"
//            threadPoolKey = "threadPoolKey"
            )
    public String service() {
        System.out.println("service run by: " + Thread.currentThread().getName());
//        try {
//            Thread.sleep(100000000);
//        } catch (Exception e) {
//            System.out.println("sleep interupted");
//        }
//        return "good result";
        throw new CustonException("something wrong in serviceSync");
    }

    @HystrixCommand(fallbackMethod="handleFallback",
//            ignoreExceptions = {CustonException.class},
            commandKey = "commandKey2",
            groupKey = "groupKey"
//            threadPoolKey = "threadPoolKey2"
    )
    public String anotherService() {
        System.out.println("service run by: " + Thread.currentThread().getName());
//        try {
//            Thread.sleep(100000000);
//        } catch (Exception e) {
//            System.out.println("sleep interupted");
//        }
//        return "good result";
        throw new CustonException("something wrong in serviceSync");
    }

    @HystrixCommand(fallbackMethod = "handleFallback")
    public Future<String> serviceAsync() {
        return new AsyncResult<String>() {
            @Override
            public String invoke() {
                throw new RuntimeException("something wrong in serviceAsync");
//                return service();
            }
            @Override
            public String get() {
                return invoke();
            }
        };
    }
    public String handleFallback() {
        return "fallback result";
    }
}
