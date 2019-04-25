package com.didispace.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;
import rx.plugins.RxJavaPlugins;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class testRxJavaMain {
    public static void main(String[] args) {

        /**
         * 重写RxJavaHooks的onObservableStart字段
         * RxJavaHooks的所有字段和方法都是static
         */
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart =
                (t1, t2) -> {
                System.out.println("my custom onObservableStart");
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(t1, t2);
        };
        RxJavaHooks.setOnObservableStart(onObservableStart);
//        testCreate(args);
//        testFrom(args);
//        testFuture();
//        testDefer();
        test(args);
    }
    public static void testCreate(String[] args) {
        // normal impl
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber){
//                subscriber.onNext("1");
//                subscriber.onNext("2");
//                subscriber.onCompleted();
//            }
//        });

        /**
         * lamda impl
         */
        Observable<String> observable = Observable.create((Subscriber<? super String> subscriber) -> {
            for (int i=0; i<args.length; i++){
                subscriber.onNext(args[i]);
            }
            subscriber.onNext("extra1");
            subscriber.onNext("extra2");
            subscriber.onCompleted();
        });

        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                System.out.println("start");
            }
            @Override
            public void onCompleted() {
                System.out.println("finished");
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(String s) {
                System.out.println(s+ " received");
            }
        };
        observable.subscribe(subscriber);
    }

    /**
     * from 会将array里的东西自动onNext传出去 ,最后再OnComplete
     * @param args
     */
    public static void testFrom(String[] args) {
        Observable<String> observable = Observable.from(args);
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onNext(String str) {
                System.out.println(str);
            }
        });
    }

    public static void testFuture() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(String.format("callable run in thread " + Thread.currentThread().getName()));
                Thread.sleep(10000);
                return "run in callable call function";
            }
        };
        FutureTask<String> task = new FutureTask<>(callable);
        System.out.println("instance future task");
        Observable<String> observable = Observable.from(task);
        System.out.println("instance observable");
        executorService.submit(task);
        executorService.shutdown();
        //使用observable的方式进行注册消费者，如果注册的future没有执行完成，会柱塞当前线程
        observable.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("observable run complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observable error");
            }

            @Override
            public void onNext(String s) {
                System.out.println(String.format("observer onNext run in thread " + Thread.currentThread().getName()));
                System.out.println("observer get msg " + s);
            }
        });
    }

    public static void testDefer() {
//        Observable justObservable = Observable.just("21");
        Observable deferObservable = Observable.defer(new Func0<Observable<String>>() {

            @Override
            public Observable<String> call() {
                return Observable.just("12");
            }
        });

        Subscriber<String> justSubscriber = new Subscriber<String>(){
            @Override
            public void onStart() {
                System.out.println("overwrite onStart");
            }

            @Override
            public void onCompleted() {
                System.out.println("observable run complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("observable error");
            }

            @Override
            public void onNext(String s) {
                System.out.println("observer get msg " + s);
            }
        };

//        justObservable.subscribe(justSubscriber);

        deferObservable.subscribe(justSubscriber);
    }

    public static void test(String[] args) {
        Observable<String> observable = Observable.from(args)
                .doOnNext((String t) ->
                        System.out.println("iter to " + t))
                .doOnError((Throwable t) ->
                        System.out.println(t.getMessage()))
                .doOnCompleted(() -> System.out.println("completed"));
        observable.subscribe();
    }
}
