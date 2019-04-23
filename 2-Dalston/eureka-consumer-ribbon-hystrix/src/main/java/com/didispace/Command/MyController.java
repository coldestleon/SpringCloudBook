package com.didispace.Command;

import com.didispace.Command.MyCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.Future;

@RestController
public class MyController {
    @Autowired
    CommandService service;

    @GetMapping("/command")
    public String command() {
        return service.testobserval();
    }

    @Service
    class CommandService {

        /**
         * sync
         * @return
         */
        public String service() {
            HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
            MyCommand command = new MyCommand(key);
            return command.execute();
        }

        /**
         * async
         * @return
         */
        public String serviceAsync() {
            HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
            MyCommand command = new MyCommand(key);
            String result = "";
            Future<String> task = command.queue();
            try{
                result = task.get();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                return result;
            }
        }

        public String testobserve() {
            HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
            MyCommand command = new MyCommand(key);
            System.out.println("to observe now");
            Observable<String> hot = command.observe();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("subscribe first");
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
                    System.out.println("complete");
                }
            });
            System.out.println("subscribe second");
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
                    System.out.println("complete 2");
                }
            });
            return "testobserve";
        }

        public String testobserval() {
            String val = "default";
            HystrixCommandGroupKey key = HystrixCommandGroupKey.Factory.asKey("haha");
            MyCommand command = new MyCommand(key);
            System.out.println("toObservable now");
            Observable<String> cold = command.toObservable();
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("subscribe first");
            cold.subscribe(new Subscriber<String>() {
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
                    System.out.println("completedd");
                }
            });
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("subscribe second");
            cold.subscribe(new Subscriber<String>() {
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
                    System.out.println("completeddd");
                }
            });
            return "testobserval";
        }
    }
}
