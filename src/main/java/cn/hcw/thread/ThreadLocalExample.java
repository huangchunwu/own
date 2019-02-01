package cn.hcw.thread;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 线程局部变量
 * 应用：可以用于单个线程的参数的存储，模板方法的上下文
 * 比如Java7中的SimpleDateFormat不是线程安全的，可以用ThreadLocal来解决这个问题：
 * 注意事项：防止使用线程池，导致内存泄露
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
