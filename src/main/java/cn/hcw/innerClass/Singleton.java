package cn.hcw.innerClass;

/**
 * 内部类的使用
 *
 * 内部类加载的时候 不会初始化 主类
 * Created by huangchunwu on 2019/1/30.
 */
public class Singleton {

    //private final static Singleton instance=new Singleton();

    private Singleton(){
        System.out.println("init");
    }

    public static void main(String[] args) {
       new  InnerClassExample.inner().getInstance();
    }
}
