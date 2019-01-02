package com.aidilude.concurrency.example.threadPool;

import com.aidilude.concurrency.example.task.CheckTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExample1 {

    public static void main(String[] args) {

        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, workQueue);

        CheckTask checkTask = new CheckTask(threadPoolExecutor);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(checkTask, 1, 2, TimeUnit.SECONDS);

        int count = 1;
        do {
            final int temp = count;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    int t = temp;
                }
            });
            count++;
        }while (true);


    }

}