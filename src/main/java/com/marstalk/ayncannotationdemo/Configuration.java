package com.marstalk.ayncannotationdemo;

import com.marstalk.ayncannotationdemo.framework.MyThreadPoolExecutor;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean("myExecutor")
    public ExecutorService getAsyncExecutor() {
        MyThreadPoolExecutor threadPoolExecutor = new MyThreadPoolExecutor(50,
                50, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        return threadPoolExecutor;
    }
}
