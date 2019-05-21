package cn.hcw.single;


/**
 * 单例必考
 */
public class SingleInstance {

    private static volatile SingleInstance instance;//防止 指令重排序

    private SingleInstance(){ // 让外部不能new SingleInstance

    }


    public static SingleInstance getInstance() {

        if (instance == null){//如果另外一个线程执行这里，发现instance已经调用了构造函数，对象已经不为空，但是执行2
            synchronized (SingleInstance.class){//锁住这个类
                if (instance ==null){
                    instance = new SingleInstance();// 1 先加载类，分配内存   2  构造函数  3 将内存地址指向instance  如果 3,2 顺序反了
                }
            }
        }
        return instance;
    }


}
