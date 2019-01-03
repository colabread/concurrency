package com.aidilude.concurrency.example.blockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueExample2 {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < 11; i++) {
            System.out.println(String.valueOf(blockingQueue.add(i)) + "：" + i);
        }
    }

}