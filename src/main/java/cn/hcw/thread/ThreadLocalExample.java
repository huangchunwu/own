package cn.hcw.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 线程局部变量
 * 应用：可以用于单个线程的参数的存储，模板方法的上下文
 * 比如Java7中的SimpleDateFormat不是线程安全的，可以用ThreadLocal来解决这个问题：
 * 注意事项：防止使用线程池，导致内存泄露
 *
 * 每个线程维护了一个threadLocalMap，ThreadLocal作为弱引用的KEY，线程局部变量作为value
 * 通常情况，当线程结束，ThreadLocal变量 作为 threadLocalMap的弱引用key，则在GC的时候就会被回收，
 * 但是如果threadLocalMap的弱引用key的value还被强引用的话，此时即使key==null,threadLocalMap依然会内存泄露
 * 当线程存活比较久，比如线程池，要记得手动清空threadLocalMap。
 * 另外threadLocalMap的entry，当线程的局部变量值即value部分如果超过临界值，则会引发entry的GC
 * 线程拥有的局部变量 如果超过容量的2/3（默认容量10个），则会涉及到ThreadLocalMap中Entry的回收；
 * Created by huangchunwu on 2019/2/1.
 */
public class ThreadLocalExample {

    private static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();

    //使用InheritableThreadLocal可以实现多个线程访问ThreadLocal的值。
    private static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();


    public ThreadLocalExample(){
        threadLocal.set(Thread.currentThread().getName()+":initValue");
        inheritableThreadLocal.set(Thread.currentThread().getName()+":initValue");
    }


    /**
     * threadLocal的get方法,线程局部变量，其他线程访问不了，线程结束，threadLocal的get方法被销毁
     * Thread t = Thread.currentThread(); //native 本地方法，直接操作C库的
       ThreadLocalMap map = getMap(t);
     */
    @Test
    public void testGet(){
        System.out.println(Thread.currentThread().getName()+":begin:"+threadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
                System.out.println(Thread.currentThread().getName()+":"+inheritableThreadLocal.get());
            }
        }).start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("autodestroy");
            }
        }).start();


        System.out.println("end"+threadLocal.get());

    }

    @Test
    public void testThreadPool(){
        ExecutorService  executorService = Executors.newFixedThreadPool(5);
        for (int i=0;i<5;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    threadLocal.set(Thread.currentThread().getName()+"-autodestroy");
                }
            });
        }


        for (int i=0;i<5;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+":"+threadLocal.get());
                }
            });
        }


        try {
            Thread.currentThread().sleep(7770*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
