package com.aidilude.concurrency.example.count;

import com.aidilude.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@UnThreadSafe
public class CountExample1 {

    //总请求数
    public static Integer clientTotal = 5000;

    //总并发数
    public static Integer threadTotal = 200;

    public static Integer count = 0;

    private static void add(){
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);   //信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);   //计数器
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                add();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count：{}", count);
    }

}
