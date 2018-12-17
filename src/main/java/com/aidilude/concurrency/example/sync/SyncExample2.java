package com.aidilude.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SyncExample2 extends SyncExample1 {

    public static void main(String[] args) {
        SyncExample2 example1 = new SyncExample2();
//        SyncExample2 example2 = new SyncExample2();
        ExecutorService executor = Executors.newCachedThreadPool();   //线程池是为了下面两段代码同时执行，而非顺序执行
//        executor.execute(() -> {
//            example1.test1();
//        });
//        executor.execute(() -> {
//            example1.test1();
//        });
        executor.execute(() -> {
            example1.test2();
        });
        executor.execute(() -> {
            example1.test2();
        });
    }

}
