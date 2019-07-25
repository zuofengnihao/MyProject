package com.takozy.one;

/**
 * JVM 中 boolean 基本类型的保存是用 int 的值保存
 *
 * 在栈中 除了 long&double 类型 所有的类型的大小都以虚拟机位数看齐
 * 32位虚拟机中 都为32位4byte 64位虚拟机中都为64位8byte(long&double也是)
 *
 * boolean类型在堆中只占 32bit(4byte) 而 boolean[] 数组是以 byte[] 数组实现 占8bit
 *
 */
public class Foo {
    public static void main(String[] args) {
        // flag = 1 true的值为1 false的值为0 boolean类型默认值0(false)
        boolean flag = true;
        // if (flag != 0)
        if (flag) System.out.println("Hello, java!");
        // if (flag == 1)
        if (flag == true) System.out.println("Hello, JVM!");
    }
}
