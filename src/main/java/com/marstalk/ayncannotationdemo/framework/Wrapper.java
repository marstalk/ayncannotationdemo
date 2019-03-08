package com.marstalk.ayncannotationdemo.framework;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wrapper implements Runnable {

    private final Runnable task;

    private final Thread caller;

    public Wrapper(Runnable task, Thread caller) {
        this.task = task;
        this.caller = caller;
    }

    @Override
    public void run() {
        Iterable<ThreadLocal<?>> vars = null;
        try {
            vars = copy(caller);
        } catch (Exception e) {
            throw new RuntimeException("error when coping Threads", e);
        }
        try {
            task.run();
        } finally {
            for (ThreadLocal<?> var : vars) {
                var.remove();
            }
        }
    }


    private static Collection<ThreadLocal<?>> copy(Thread caller) throws Exception {
        List<ThreadLocal<?>> threadLocals = new ArrayList<>();
        Field field = Thread.class.getDeclaredField("inheritableThreadLocals");
        field.setAccessible(true);
        Object map = field.get(caller);

        Method method = ThreadLocal.class
                .getDeclaredMethod("createInheritedMap", Class.forName("java.lang.ThreadLocal$ThreadLocalMap"));
        method.setAccessible(true);
        Object o = method.invoke(null, map);

        Field field2 = Thread.class.getDeclaredField("inheritableThreadLocals");
        field2.setAccessible(true);
        field2.set(Thread.currentThread(), o);

        Field table = Class.forName("java.lang.ThreadLocal$ThreadLocalMap").getDeclaredField("table");
        table.setAccessible(true);
        Object tbl = table.get(o);
        int length = Array.getLength(tbl);
        for (int i = 0; i < length; i++) {
            Object entry = Array.get(tbl, i);
            Object value = null;
            if (entry != null) {
                Method referentField = Class.forName("java.lang.ThreadLocal$ThreadLocalMap$Entry").getMethod(
                        "get");
                referentField.setAccessible(true);
                value = referentField.invoke(entry);
                threadLocals.add((ThreadLocal<?>) value);
            }
        }
        return threadLocals;
    }
}
