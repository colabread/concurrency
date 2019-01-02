package com.aidilude.concurrency.example.task;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class CheckTask implements Runnable {

    private ThreadPoolExecutor threadPoolExecutor;

    public CheckTask(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    @Override
    public void run() {
        log.info("getTaskCount：{}，getCompletedTaskCount：{}，getPoolSize：{}，getActiveCount：{}", threadPoolExecutor.getTaskCount(), threadPoolExecutor.getCompletedTaskCount(), threadPoolExecutor.getPoolSize(), threadPoolExecutor.getActiveCount());
    }
}