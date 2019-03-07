package com.marstalk.ayncannotationdemo;

import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@org.springframework.context.annotation.Configuration
public class Configuration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolExecutor(3, 3, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
    }
}
