package com.takozy.two;

/**
 *  加载阶段知识点:
 *      类加载器:
 *
 *          启动类加载器: bootstrap class loader 无java类 c语言实现
 *          其他类加载器均为 java.lang.ClassLoader 子类
 *          负责最为基础的、最重要的类的加载 如 JRE的bin目录下的jar包中的类
 *          以及由虚拟机参数 -Xbootclasspath 指定的类
 *
 *          扩展类加载器: extension class loader 父类是启动类加载器
 *          负责加载相对次要的但又通用的类,比如存放在JRE的bin/ext目录下的ja包
 *          中的类以及由变量java.ext.dirs指定的类
 *          在Java9中将其改名为 平台类加载器 platform class loader
 *          JavaSE中除了少数几个关键模块(java.base)是由启动类加载器加载的
 *          其他的模块均由平台类加载器加载
 *
 *          应用类加载器: application class loader 父类是扩展类加载器
 *          负载加载应用程序路劲下的类(-cp/-classpath)
 *
 *      类加载器除了加载功能外 还提供了命名空间的作用 类的唯一性由类加载器实例
 *      以及类的全名一同确定
 *
 *      双亲委派:
 *      每当一个类加载器接受到加载请求 它会将请求转发给父类加载器 父类没有找到所请求的
 *      的类的情况下 该类加载器才会去尝试加载
 *
 *
 */
public class Foo {
    static boolean boolValue;
    public static void main(String[] args) {
        boolValue = true;
        if (boolValue) System.out.println("Hello, java!");
        if (boolValue == true) System.out.println("Hello, JVM!");


    }
}
