package com.example;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
//        main.firstRx();
//        main.easyUse();
        main.fun("Hello World");
    }

    private void fun(String s) {
        Observable.just(s).map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return s.length();
            }
        }).map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                return integer * 10;
            }
        }).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "结果是" + integer;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

    }

    // 稍微简化的使用方法
    private void easyUse() {
        Observable.just("Hello World").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });
    }

    //RxJava初体验
    private void firstRx() {
        // Observable 是被观察者, 它来发射初始的数据源
        // 可以通过create等来操作符来创建
        // create操作符:
        // 创建一个Observable对象
        // 参数是一个OnSubscribe 接口对象
        // 它表示有 观察者 观察该被观察者的时候, 会执行call方法
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello World");
                subscriber.onCompleted();

            }
        });
        // Subscriber 是观察者
        // 它的泛型代表, 它想接受什么数据
        // 他有三个方法
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                // 当这个事件完成了会调用一次该方法
                // 通常很少使用
                System.out.println("完成");
            }

            @Override
            public void onError(Throwable e) {

                // 在整个事件流里, 有任何异常的发生
                // 都会直接回调该方法(包括空指针)
            }

            @Override
            public void onNext(String s) {
                // 该方法是观察者最重要的方法
                // 每一次被观察者 发生改变的时候都会调用该方法
                System.out.println(s);
            }
        };
        // 让被观察者 被 观察者 观察
        observable.subscribe(subscriber);
    }
}
