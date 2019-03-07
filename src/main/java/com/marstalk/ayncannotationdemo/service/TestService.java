package com.marstalk.ayncannotationdemo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class TestService {

    @Async
    public Future<String> calculate() throws InterruptedException {
        Thread.sleep(1000);
        return new AsyncResult<>("1");
    }


    @Async
    public Future<String> calculate2() throws InterruptedException {
        Thread.sleep(2000);
        return new AsyncResult<>("2");
    }

}
