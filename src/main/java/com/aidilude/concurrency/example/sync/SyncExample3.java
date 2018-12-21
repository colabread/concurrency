package com.aidilude.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SyncExample3 {

    /**
     * 修饰静态方法
     * 作用对象：该类的所有对象
     * 使用场景：多例
     */
    public static synchronized void test1(){
        for (int i = 0; i < 10; i++) {
            log.info("test1 - {}", i);
        }
    }

    /**
     * 修饰类
     * 作用对象：该类的所有对象
     * 使用场景：多例
     */
    public static void test2(){
        synchronized (SyncExample3.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - {}", i);
            }
        }
    }

    public static void main(String[] args) {
        SyncExample3 example1 = new SyncExample3();
        SyncExample3 example2 = new SyncExample3();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2();
        });
        executorService.execute(() -> {
            example2.test2();
        });
    }

}