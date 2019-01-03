package com.aidilude.concurrency.example.blockingQueue;

import com.aidilude.concurrency.example.element.Person;
import com.aidilude.concurrency.example.element.Task;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueExample3 {

    public static void main(String[] args) {
        BlockingQueue<Task> blockingQueue = new PriorityBlockingQueue<Task>(3);
        Task t1 = new Task(12, "fuck");
        Task t2 = new Task(31, "uzi");
        Task t3 = new Task(16, "faker");
        Task t4 = new Task(8, "clearlove");
        blockingQueue.add(t1);
        blockingQueue.add(t2);
        blockingQueue.add(t3);
        blockingQueue.add(t4);
        for (int i = 0; i < 4; i++) {
            System.out.println(blockingQueue.remove());
        }
    }

}