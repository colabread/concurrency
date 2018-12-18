package com.aidilude.concurrency.example.publish;

import com.aidilude.concurrency.annotation.Recommend;
import com.aidilude.concurrency.annotation.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 饿汉模式一
 * 静态域单例模式
 * 单实例在类装载时创建
 */
@ThreadSafe
@Recommend
public class SingletonExample2 {

    private SingletonExample2(){

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        int hashcode = getInstance().hashCode();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                if(getInstance().hashCode() != hashcode)
                    System.out.println("NO");
            });
        }
        System.out.println("END");
    }

}