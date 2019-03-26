package cn.hcw.classLoad;

/**
 * 对象实例化过程
 * 虚拟机接受new指令，
 * 1：类加载过程，父类静态变量与静态方法块执行完毕，子类静态变量与静态方法执行完毕
 * 2：再执行父类的实例变量与构造方法，最后执行子类的实例化变量与构造方法
 *
 *  下面这个输出，就比较有意思
 *
 */
public class ObjectCreateBEx extends ObjectCreateAEx{

    private int a = 10;

    private static int b = 11;

    private static final  int c = 12;

    ObjectCreateBEx(int a){
        a = a;
    }


    @Override
    public int  getAV() {
       return a;
    }


    public static void main(String[] args) {
        ObjectCreateBEx  aEx =  new ObjectCreateBEx(100);
    }
}
