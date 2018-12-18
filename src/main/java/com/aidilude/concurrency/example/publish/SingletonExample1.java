package com.aidilude.concurrency.example.publish;

import com.aidilude.concurrency.annotation.Recommend;
import com.aidilude.concurrency.annotation.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 懒汉模式
 * 双重检测单例模式
 */
@ThreadSafe
@Recommend
public class SingletonExample1 {

    private SingletonExample1(){

    }

    //new一个对象的cpu指令步骤：
    //1、memory = allocate() 为对象分配内存
    //2、ctorInstance() 初始化对象
    //3、instance -> memory 设置instance指向刚刚分配的内存
    //上面第1步和第2步是可以进行重排序的，如果变成了1、3、2的顺序，那么在多线程中就会出现问题
    //如现在线程A到了判断2，发生指令重排序，执行了1、3指令，此时线程B到达判断1，发现instance已经有了，就直接返回了，但instance还没初始化，这时就出问题了
    //那么使用volatile关键字修饰instance，就阻止了生成instance的重排序
    private volatile static SingletonExample1 instance;

    public static SingletonExample1 getInstance(){
        if(instance == null){   //判断1
            synchronized (SingletonExample1.class) {
                if(instance == null){   //判断2
                    instance = new SingletonExample1();
                }
            }
        }
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