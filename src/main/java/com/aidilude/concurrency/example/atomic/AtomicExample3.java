package com.aidilude.concurrency.example.atomic;

import com.aidilude.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe
@Slf4j
public class AtomicExample3 {

    @Getter
    private volatile int count = 100;   //必须是int基本类型

    public static void main(String[] args) {
        AtomicExample3 example3 = new AtomicExample3();
        AtomicIntegerFieldUpdater<AtomicExample3> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample3.class, "count");
        if(updater.compareAndSet(example3, 100, 120)){
            log.info("update1 success,count:{}", example3.getCount());
        }

        if(updater.compareAndSet(example3, 100, 120)){
            log.info("update2 success,count:{}", example3.getCount());
        }else{
            log.info("update3 failed,count:{}", example3.getCount());
        }
    }

}
