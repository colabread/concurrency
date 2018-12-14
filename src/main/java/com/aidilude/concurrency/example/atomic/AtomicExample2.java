package com.aidilude.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicExample2 {

    public static AtomicBoolean flag = new AtomicBoolean(false);

    public static void main(String[] args) {
        flag.compareAndSet(true, false);
    }

}
