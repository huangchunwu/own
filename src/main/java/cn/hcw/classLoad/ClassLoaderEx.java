package cn.hcw.classLoad;

/**
 * 类加载机制过程
 *   装载（加载class解释成虚拟机的方法区的运行期数据结构）----验证（解析class文件合法性）
 *   ---准备(类变量设初始值，而非实例变量)---解析（符号引用转成直接引用）----初始化（类变量与static语句初始化）--使用--卸载
 *
 *
 * 静态变量 别名 类变量
 * 成员变量 别名 实例变量
 *
 * 类初始化做了什么
 * 类的初始化会从祖先类到子类、按出现顺序，对类变量的初始化语句、静态初始化语句块依次进行初始化
 *
 * 类初始化的条件
 * (1).(无论直接通过new创建出来的，还是通过反射、克隆、序列化创建的)创建某个类新的实例
 * (2).使用某个类的静态方法
 * (3).访问某个类或接口的静态字段
 * (4).调用JavaAPI中的某些反射方法
 * (5).初始化某个类的子类(要求其祖先类都要被初始化，否则无法正确访问其继承的成员)
 * (6).启动某个标明为启动类的类(含有main()方法)
 *
 *
 * 这里需要明白什么是类初始化，什么是类实例化，以及类的实例对象的初始化
 * 如前面所述，类初始化时对类(静态)变量赋予指定的初始值，类初始化之后就可以访问类的静态字段和方法，而访问类的非静态(实例)字段和方法，就需要创建类的对象实例，故类的实例化是在类的初始化之后，是在堆上创建一个该类的对象。
 * 类的静态方法和字段属于类，作为类型数据保存在方法区，其生命周期取决于类，而实例方法和字段位于Java堆，其生命周期取决于对象的生命周期。
 * 类的初始化会从祖先类到子类、按出现顺序，对类变量的初始化语句、静态初始化语句块依次进行初始化。而对类实例的初始化也类似，会从祖先类到子类、按出现顺序，对类成员的初始化语句、实例初始化块、构造方法依次进行初始化。
 */
public class ClassLoaderEx {

    ClassLoaderEx(){
        System.out.println("构造函数 init");
    }

    static {
        i=4;
        System.out.println("static");
    }

    static int i = 3;

    public static void main(String[] args) {
        System.out.println("i="+i);
    }

}
