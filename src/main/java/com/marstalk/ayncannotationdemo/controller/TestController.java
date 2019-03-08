package com.marstalk.ayncannotationdemo.controller;


import com.marstalk.ayncannotationdemo.framework.ThreadLocalHolder;
import com.marstalk.ayncannotationdemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @GetMapping("/test/{id}")
    public String test(@PathVariable String id) throws InterruptedException, ExecutionException {
        long begin = System.currentTimeMillis();

        ThreadLocalHolder.setThreadLocal(id);
        ThreadLocalHolder.setInheritableThreadLocal("inherited " + id);

        CountDownLatch cdl = new CountDownLatch(3);
        Future<String> calculate = testService.calculate(1000L, cdl, id);
        Future<String> calculate2 = testService.calculate(5000L, cdl, id);
        Future<String> calculate3 = testService.calculate(6000L, cdl, id);
        cdl.await();
        while (true) {
            long count1 = cdl.getCount();
            if (count1 == 0) {
                System.out.println(calculate.get());
                System.out.println(calculate2.get());
                System.out.println(calculate3.get());
                break;
            }
        }

        System.out.println("threadlocal: " + ThreadLocalHolder.getThreadLocal());
        System.out.println(id + "request cost: " + (System.currentTimeMillis() - begin)/1000);
        return "success";
    }
}
