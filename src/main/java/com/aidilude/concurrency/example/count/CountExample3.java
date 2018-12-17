package com.aidilude.concurrency.example.count;

import com.aidilude.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@UnThreadSafe
public class CountExample3 {

    //总请求数
    public static Integer clientTotal = 5000;

    //总并发数
    public static Integer threadTotal = 200;

    //volatile变量只保证可见性，不保证原子性
    public static volatile Integer count = 0;

    private static void add(){
        count++;
        //此时对volatile变量执行++操作会执行以下三步：
        //1、volatile读：保证当前线程得到的值是最新的
        //2、++操作
        //3、volatile写：保证将最新的值更新到主存
        //由此可见当并发来临，只能保证可见性，不能保证原子性
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
