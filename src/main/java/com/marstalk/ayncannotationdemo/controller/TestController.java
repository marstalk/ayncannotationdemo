package com.marstalk.ayncannotationdemo.controller;


import com.marstalk.ayncannotationdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/test")
    public String test() throws InterruptedException, ExecutionException {
        long begin = System.currentTimeMillis();
        Future<String> calculate = testService.calculate();
        Future<String> calculate2 = testService.calculate2();
        int count = 2;
        while (count > 0) {
            if (calculate.isDone()) {
                System.out.println(calculate.get());
                count--;
            }
            if (calculate2.isDone()) {
                System.out.println(calculate2.get());
                count--;
            }
        }

        System.out.println(System.currentTimeMillis() - begin);
        return "success";
    }

}
