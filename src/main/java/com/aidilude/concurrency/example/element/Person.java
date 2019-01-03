package com.aidilude.concurrency.example.element;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
public class Person implements Delayed {

    private Integer age;

    private String name;

    private Date startTime;

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime.getTime() - new Date().getTime();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Person person = (Person)o;
        return age.intValue() - person.getAge().intValue();
    }

}