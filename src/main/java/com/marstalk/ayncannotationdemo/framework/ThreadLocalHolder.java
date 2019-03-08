package com.marstalk.ayncannotationdemo.framework;

public class ThreadLocalHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();


    public static String getThreadLocal() {
        return threadLocal.get();
    }

    public static void setThreadLocal(String str) {
        threadLocal.set(str);
    }

    public static String getInheritableThreadLocal() {
        return inheritableThreadLocal.get();
    }

    public static void setInheritableThreadLocal(String str) {
        inheritableThreadLocal.set(str);
    }
}
