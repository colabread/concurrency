package com.aidilude.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample1 {

    /**
     * 修饰对象
     * 作用域：同步块内
     * 作用对象：只对当前对象（this）
     */
    public void test1(){
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    /**
     * 修饰普通方法
     * 作用域：方法内
     * 作用对象：只对当前对象（this）
     */
    public synchronized void test2(){
        for (int i = 0; i < 10; i++) {
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
