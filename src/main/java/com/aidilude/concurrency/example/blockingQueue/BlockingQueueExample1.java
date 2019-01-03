package com.aidilude.concurrency.example.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample1 {

    public static void main(String[] args) throws Exception {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
        for (int i = 0; i < 11; i++) {
//            System.out.println(String.valueOf(blockingQueue.add(i)) + "：" + i);
//            System.out.println(String.valueOf(blockingQueue.offer(i)) + "：" + i);
//            blockingQueue.put(i);
//            System.out.println(i);
//            System.out.println(String.valueOf(blockingQueue.offer(i, 3, TimeUnit.SECONDS)) + "：" + i);
        }
        blockingQueue.add(9);
        blockingQueue.add(7);
        blockingQueue.add(1);
        blockingQueue.add(34);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(blockingQueue.element());
//        }
        for (int i = 0; i < 100; i++) {
            System.out.println(blockingQueue.peek());
        }
    }

}