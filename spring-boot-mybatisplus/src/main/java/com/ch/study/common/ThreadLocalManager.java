package com.ch.study.common;

public class ThreadLocalManager {
    private static ThreadLocal<CommonSession> threadLocal = new ThreadLocal<>();

    public static void setValue(CommonSession value) {
        threadLocal.set(value);
    }

    public static CommonSession getValue() {
        return threadLocal.get();
    }

    public static void removeValue() {
        threadLocal.remove();
    }
}
