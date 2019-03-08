package com.marstalk.ayncannotationdemo.dao;


import com.marstalk.ayncannotationdemo.framework.ThreadLocalHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

    public String get() {
        return ThreadLocalHolder.getThreadLocal();
    }

}
