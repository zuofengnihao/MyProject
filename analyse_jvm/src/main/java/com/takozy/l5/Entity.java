package com.takozy.l5;

public class Entity {
    public static int staticField;

    public int instanceField = 2;

    static {
        staticField = 1;
        System.out.println("Class Loading com.takozy.l5.Entity....");
    }

    public static void staticMethod() {
        System.out.println("Entity static method run...");
    }

    public Entity(){
        System.out.println("Entity Construct....");
    }

    public void instanceMethod() {
        System.out.println("Entity instance method run...");
    }

}
