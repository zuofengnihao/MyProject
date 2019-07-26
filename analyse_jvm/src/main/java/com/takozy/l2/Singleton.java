package com.takozy.l2;

/**
 * 单例延时初始化
 * 只有调用Singleton.getInstance()方法时LazyHolder类才会被初始化
 *
 * !!!! 数组的声明不会初始化该类
 */
public class Singleton {
    private Singleton(){}
    public static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();
        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }
    //当flag为true时 不会打印(LazyHolder.<clinit>)
    //数组的声明不会导致类的初始化(包括链接) 但是会加载
    public static Object getInstance(boolean flag){
        if (flag) return new LazyHolder[2];
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Singleton.getInstance(true);
        System.out.println("----------------");
        Singleton.getInstance(false);
    }
}


