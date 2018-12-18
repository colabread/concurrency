package com.aidilude.concurrency.example.publish;

import com.aidilude.concurrency.annotation.Recommend;
import com.aidilude.concurrency.annotation.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 饿汉模式二
 * 静态块单例模式
 * 单实例在类装载时创建
 *
 * 不同的静态代码块是按编写顺序执行的
 */
@ThreadSafe
@Recommend
public class SingletonExample3 {

    private SingletonExample3(){

    }

    private static SingletonExample3 instance;

    static {
        instance = new SingletonExample3();
    }

    public static SingletonExample3 getInstance(){
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