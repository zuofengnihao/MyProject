package com.takozy.l3;

/**
 * 重载与重写:
 *
 * 重载:方法名相同 参数列表不同
 *
 *      实际上利用字节码工具可以修改存在 方法名相同 参数列表相同 但返回值不同的方法[不需要转型]
 *      JVM识别方法是根据方法所在的类 方法名 以及方法描述符来定位的 方法的描述符就包含参数列表与返回值
 *      但如果名字相同且方法描述符也相同时 会在类加载的验证阶段报错
 *
 *      重载的方法在编译过程中即可完成识别 Java编译器会根据传入参数的类型来选取方法选取过程分三步:
 *
 *      1.在不考虑对基本类型自动拆装箱以及可变长参数的情况下选取
 *      2.在1阶段中没找到那么允许自动拆装箱
 *      3.在2阶段中没找到那么允许可变长
 *
 *      如果找到了多个方法 虚拟机会选择最贴切的方法 贴切程度取决于形参的继承关系
 *      以下面的例子为例 虚拟机觉得null值 String与Object中 String更贴切 因为String是Object的子类
 *
 * 重写:子类中定义了一个与父类中非私有非静态的 参数列表相同返回值相同(可以是继承关系)的方法
 *      如果两个方法都是静态的子类将隐藏父类的方法
 *
 * JVM方法的静态绑定与动态绑定:
 *      静态绑定是指在解析时便能直接识别目标方法的情况动
 *      态绑定是指需要在运行过程中根据调用者的动态类来识别目标方法的情况
 *
 * 五种调用指令: (详见test)
 *      invokestatic:调用静态方法
 *      invokespecial:调用私有方法 构造器 以及super关键字调用父类的实例方法或构造器和实现接口的默认方法
 *      invokevirtual:调用非私有的实例方法
 *      invokeinterface:调用接口方法
 *      invokedynamic:调用动态方法(后面介绍)
 *
 *  invokestatic和invokespecial 能够直接识别 属于静态绑定
 *  invokevirtual和invokeinterface 在绝大数情况下是动态绑定 除final方法(Hotspot优化)
 *
 *  调用指令的符号引用:
 *      符号引用储存在class文件的常量池中 可分为:
 *      interfacemethodref:接口符号引用
 *      methodref:非接口符号引用
 *      执行使用了符号引用的字节码前需要解析成实际引用
 *      静态绑定的方法的实际引用是指向改方法的指针
 *      而动态绑定的方法的实际引用是一个方法表的索引
 *
 *  虚方法的调用:
 *      invokevirtual和invokeinterface都属于虚方法的调用
 *      都属于动态绑定的方法 其实际引用是一个方法表的索引
 *
 *  方法表:
 *      方法表在类的加载过程中的链接阶段被构造
 *      invokevirtual所使用的virtual method table vtable
 *      invokeinterface所使用的interface method table itable
 *      方法表本质上是一个数组 每个元素指向一个当前类及其祖先类中非私有的实例方法
 *      方法表特质:1.子类方法表包含父类方法表中的所有方法
 *                2.子类方法表中的索引值与它父类方法索引值一致
 *
 *  动态绑定的调用:
 *      实际上动态绑定与静态绑定相比 仅仅多出几个内存解引用操作
 *      访问栈上的调用者 读取调用者类型 读取该类型的方法表 读取方法表中某个索引值对应的目标方法
 *
 *  动态绑定的优化:
 *      内存内联:
 *          缓存虚方法调用者的动态类型以及该类型的对应的目标方法
 */
public class Foo {

    public static void invoke(Object ojb, Object... args) {
        System.out.println("invoke m1");
    }

    public static void invoke(String s, Object obj, Object... args) {
        System.out.println("invoke m2");
    }

    public static void main(String[] args) {
        Foo.invoke(null, 1);
        Foo.invoke(null, 1, 2);
        Foo.invoke(null, new Object[]{1});
    }
}
