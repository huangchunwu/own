package cn.hcw.innerClass;

/**
 * 内部类的使用
 * 内部类加载的时候 不会初始化 主类
 * new 一个类的时候才会触发构造方法
 * 当 static 修饰 Singleton instance=new Singleton();会触发初始化
 * 否则的话不会初始化
 *
 * 什么是类初始化？要区分跟类实例对象初始化
 * 类初始化是对类(静态)变量赋予指定的初始值
 * 类的初始化会从祖先类到子类、按出现顺序，对类变量的初始化语句、静态初始化语句块依次进行初始化
 *
 * 《深入理解JAVA虚拟机》中说有且尽当5种情况，才会触发类初始化
 *  1：调用new，static 关键词(非 常量)
 *  2：反射
 *  3：初始化子类会触发父类
 *  4：虚拟机启动时候，指定执行的主类的（main方法）
 *  5：使用动态语言，包含方法句柄的类
 *  类初始化之前有： 加载--验证--准备--解析-初始化
 *
 * Created by huangchunwu on 2019/1/30.
 */
public class Singleton {

    private final  Singleton instance=new Singleton();
    private final  static int constantV =  1;

    private Singleton(){
        System.out.println("init");
    }

    public static void main(String[] args) {
       new  InnerClassExample.inner().getInstance();
    }
}
