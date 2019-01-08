package com.aidilude.concurrency.example.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample2 {

    private Map<String, Object> map = new HashMap<>();

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //共享锁
    private final Lock readLock = lock.readLock();

    //排它锁
    private final Lock writeLock = lock.writeLock();

    public Object get(String key){
        readLock.lock();    //读锁可以被多个线程共享
        try {
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }

    public Object put(String key, Object data){
        writeLock.lock();   //写锁只能被一个线程获取，当该锁的读锁和写锁都没有被持有时才能被获取
        try {
            return map.put(key, data);
        }finally {
            writeLock.unlock();
        }
    }

}