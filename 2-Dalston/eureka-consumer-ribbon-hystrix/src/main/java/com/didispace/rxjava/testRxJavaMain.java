package com.didispace.rxjava;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Func2;
import rx.plugins.RxJavaHooks;
import rx.plugins.RxJavaPlugins;

import java.util.ArrayList;
import java.util.List;

public class testRxJavaMain {
    public static void main(String[] args) {

        /**
         * 重写RxJavaHooks的onObservableStart字段
         * RxJavaHooks的所有字段和方法都是static
         */
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> onObservableStart =
                (t1, t2) -> {
                System.out.println("my custom");
                return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(t1, t2);
        };
        RxJavaHooks.setOnObservableStart(onObservableStart);
//        testCreate(args);
        testFrom(args);
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
}
