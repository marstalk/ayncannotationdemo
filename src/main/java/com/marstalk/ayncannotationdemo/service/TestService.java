package com.marstalk.ayncannotationdemo.service;

import com.marstalk.ayncannotationdemo.dao.TestDao;
import com.marstalk.ayncannotationdemo.framework.ThreadLocalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@Service
@Slf4j
public class TestService {

    @Autowired
    private TestDao testDao;

    @Async
    public Future<String> calculate(Long elapse, CountDownLatch cdl, String id) throws InterruptedException {
        ThreadLocalHolder.setThreadLocal(id);

        Thread.sleep(elapse);
        cdl.countDown();

        return new AsyncResult<>(testDao.get());
    }
}
