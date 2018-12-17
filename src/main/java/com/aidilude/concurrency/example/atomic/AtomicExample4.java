package com.aidilude.concurrency.example.atomic;

import com.aidilude.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@ThreadSafe
@Slf4j
public class AtomicExample4 {

    private static AtomicBoolean isHappen = new AtomicBoolean(false);

    //总请求数
    public static Integer clientTotal = 5000;

    //总并发数
    public static Integer threadTotal = 200;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);   //信号量
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);   //计数器
        for (int i = 0; i < clientTotal; i++) {   //循环了5000次，实际上只打印了一次log
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                test();   //调用了5000次test方法，实际上只打印了一次log
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    public static void test(){
        if(isHappen.compareAndSet(false, true)){
            log.info("isHappen:{}", isHappen.get());
        }
    }

}
