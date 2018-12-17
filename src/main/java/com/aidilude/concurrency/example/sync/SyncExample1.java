package com.aidilude.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample1 {

    /**
     * synchronized修饰代码块
     * 作用域：代码块内
     * 作用对象：只对同一个对象有效，如果是相同的类型，不同的两个对象调用是不受该关键字影响的
     */
    public void test1(){
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    /**
     * synchronized修饰方法
     * 作用域：方法内部
     * 作用对象：只对同一个对象有效，如果是相同的类型，不同的两个对象调用是不受该关键字影响的
     */
    public synchronized void test2(){
        for (int i = 0; i < 100; i++) {
            log.info("test2 - {}", i);
        }
    }

    public static void main(String[] args) {
        SyncExample1 example1 = new SyncExample1();
        SyncExample1 example2 = new SyncExample1();
        ExecutorService executor = Executors.newCachedThreadPool();   //线程池是为了下面两段代码同时执行，而非顺序执行
//        executor.execute(() -> {
//            example1.test2();
//        });
//        executor.execute(() -> {
//            example1.test2();
//        });
        executor.execute(() -> {
            example1.test2();
        });
        executor.execute(() -> {
            example2.test2();
        });
    }

}
