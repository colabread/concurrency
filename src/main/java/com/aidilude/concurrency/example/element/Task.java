package com.aidilude.concurrency.example.element;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task implements Comparable<Task> {

    private Integer height;

    private String name;

    @Override
    public int compareTo(Task o) {
        return this.height.intValue() - o.getHeight().intValue();
    }

}