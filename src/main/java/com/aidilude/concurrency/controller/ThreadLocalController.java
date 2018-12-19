package com.aidilude.concurrency.controller;

import com.aidilude.concurrency.example.threadLocal.InfoHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @GetMapping("/test")
    public Long test(){
        return InfoHolder.getInfo();
    }

}