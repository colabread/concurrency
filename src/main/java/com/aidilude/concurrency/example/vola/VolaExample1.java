package com.aidilude.concurrency.example.vola;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VolaExample1 {

    public static volatile boolean flag = false;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            log.info("initing flag is false");
            flag = true;
            log.info("updating flag is true");
//            log.info("waiting...");
//            try {
//                Thread.currentThread().sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("waiting end");
        });
        executorService.execute(() -> {
            while (!flag) {
                log.info("flag is false");
            }
            log.info("flag is true");
        });
        executorService.execute(() -> {
            while (!flag) {
                log.info("flag is false");
            }
            log.info("flag is true");
        });
    }

}