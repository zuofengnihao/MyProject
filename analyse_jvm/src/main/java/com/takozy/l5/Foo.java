package com.takozy.l5;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射:
 */
public class Foo {

    public static void main(String[] args) {

        Entity.staticMethod();

        try {
            Class<?> klass = Class.forName("com.takozy.l5.Entity");
            Method staticMethod = klass.getMethod("staticMethod");
            staticMethod.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
