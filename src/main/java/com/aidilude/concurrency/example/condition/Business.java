package com.aidilude.concurrency.example.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Business {

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    private boolean fun1_first = true;

    public void fun1() {
        lock.lock();   //获取锁
        try {
            while(!fun1_first){
                condition.await();
            }
            System.out.println("|");
            fun1_first = false;
            condition.signal();   //通知其他线程可以获取锁，不用等待了
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void fun2() {
        lock.lock();   //获取锁
        try {
            while(fun1_first){
                condition.await();   //当前线程交出锁，并开始等待
            }
            System.out.println("——");
            fun1_first = true;
            condition.signal();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}