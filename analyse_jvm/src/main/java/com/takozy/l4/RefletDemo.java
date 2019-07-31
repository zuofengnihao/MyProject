package com.takozy.l4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefletDemo {
    public static void main(String[] args) {
        try {
            Class<?> klass = Class.forName("com.takozy.l4.Entity");
            Method method = klass.getMethod("staticMethod");
            method.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
