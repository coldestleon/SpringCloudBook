package com.didispace;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

public class testRxJavaMain {
    public static void main(String[] args) {
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber){
//                subscriber.onNext("1");
//                subscriber.onNext("2");
//                subscriber.onCompleted();
//            }
//        });

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
}
