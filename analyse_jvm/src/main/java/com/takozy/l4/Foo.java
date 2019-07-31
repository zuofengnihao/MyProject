package com.takozy.l4;

/**
 *  异常表:
 *      form指针:监控开始的索引值
 *      to指针:监控结束的索引值
 *      form to 指针共同标示了该异常处理器的监控范围
 *
 *      target指针:指向异常处理器的起始位置 例如catch代码块的起始位置
 *      异常类型(比如 ArrayIndexOutOfBoundsException)
 *
 *  finally代码块:
 *      复制finally代码内容分别放在try-catch代码块正常执行路劲的出口中
 */
public class Foo {

    public static void main(String[] args) {
        try {
            mayThrowException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mayThrowException() throws Exception {

    }
}
