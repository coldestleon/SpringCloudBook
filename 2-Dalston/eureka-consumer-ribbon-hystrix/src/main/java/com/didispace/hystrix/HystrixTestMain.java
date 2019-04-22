package com.didispace.hystrix;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@EnableCircuitBreaker
public class HystrixTestMain {
    public static void main(String[] args) {
        testNonAnnotation();
    }

    /**
     * execute sync
     * queue async
     */
    private static void testNonAnnotation() {
        HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
        Command command = new Command(key);
        Future<String> task = command.queue();
        try {
            String result = task.get(1000000, TimeUnit.MILLISECONDS);
            System.out.println(result);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void testToobservable() {
        HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
        Command command = new Command(key);
        Observable<String> hot = command.observe();
        hot.subscribe(new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }
        });
    }

}

class Command extends HystrixCommand<String> {
    public Command(HystrixCommandGroupKey key) {
        super(key);
    }
    @Override
    protected String run() throws Exception {
        System.out.println("run by: " + Thread.currentThread().getName());
        Thread.sleep(10000);
        return "run";
    }

    @Override
    protected String getFallback() throws RuntimeException {
        System.out.println("fallback now");
//        return "fallback";
        throw new RuntimeException("something wrong in fallback method");
    }
}
