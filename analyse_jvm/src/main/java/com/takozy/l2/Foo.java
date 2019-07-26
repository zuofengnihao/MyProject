package com.takozy.l2;

/**
 *  加载 --> 链接 |验证->准备->解析(不一定)| --> 初始化
 *
 *  加载阶段:
 *      加载是指查找字节流 并创建类的过程 对于数组类来说 它并没有字节流
 *      是由java虚拟机直接生成
 *
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
 *  链接阶段:
 *      链接是指创建成的类合并至Java虚拟机中 使其能够执行的过程
 *      它又分为三个小阶段 : 验证 准备 解析
 *
 *      验证:
 *          验证阶段确保被加载的类能够满足Java虚拟机的约束条件
 *      准备:
 *          为被加载的类的静态字段分配内存(并未初始化)
 *          部分java虚拟机还会在此阶段构造其他跟类层次相关的数据
 *          比如说用来实现虚方法的动态绑定的方法表
 *      解析:
 *          将符号引用解析成为实际引用 如果引用指向一个未被加载的类
 *          (字段 方法)那么会触发这个类的加载(未必触发链接、初始化)
 *
 *      Java虚拟机并未规范在链接中完成解析 但是规定了:
 *      如果某些字节码使用了符号引用 那么在执行这些字节码前
 *      需要完成这些符号引用的解析
 *
 *  初始化:
 *      为标记为常量值的字段赋值，以及执行clinit()方法的过程
 *
 *      常量:被final修饰的 类型为基本类型或String
 *
 *      clinit方法:
 *          常量会被编译器标记 其初始化直接由java虚拟机完成
 *          除此之外的直接赋值操作以及静态代码块中的代码都会被
 *          java编译器置于clinit方法中
 *
 *      初始化的触发时机:
 *      1.虚拟机执行的main方法所在主类;
 *      2.new指令的目标类
 *      3.静态方法调用
 *      4.静态字段的访问
 *      5.子类初始化触发父类的初始化
 *      6.interface定义了default方法 其实现类的初始化会触发interface初始化
 *      7.使用反射
 *      8.使用MethodHandler实例在
 *
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
