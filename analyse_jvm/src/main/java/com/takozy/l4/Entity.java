package com.takozy.l4;

/**
 *
 */
public class Entity {
    public static int num;

    public byte b;

    static {
        num = 7;
    }

    public Entity() {
        System.out.println("constract...");
    }

    public static void staticMethod() {
        System.out.println("invoke static method");
    }

    public void method() {
        System.out.println("public method");
    }

    private void privatMethod() {
        System.out.println("private method");
    }
}
