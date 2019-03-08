package com.marstalk.ayncannotationdemo.controller;


import com.marstalk.ayncannotationdemo.framework.MyThreadPoolExecutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestControllerTests {


    @Test
    public void test() throws InterruptedException {


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50,
                50, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        for (int i = 0; i < 10000; i++) {
            threadPoolExecutor.submit(new Task(i));
        }

        Thread.sleep(60_1000);
    }


}

class Task implements Runnable {

    private int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        RestTemplate template = new RestTemplate();
        String forObject = template.getForObject("http://localhost:8080/test/" + System.currentTimeMillis(), String.class);
        System.out.println(forObject + id);
    }
}