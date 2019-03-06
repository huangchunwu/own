package cn.hcw.thread;

import org.junit.Test;

import java.util.concurrent.Exchanger;

/**
 * java concurrent 包
 * <p>
 * 用于2个线程交互数据使用
 * 规定一个交换点，当双方线程到达这个点后，相互交换数据
 * <p>
 * <p>
 * Created by huangchunwu on 2019/3/4.
 */
public class ExchangeTest {


    @Test
    public void testExchange() {
        final Exchanger<Integer> exchanger = new Exchanger<Integer>();


        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.print(Thread.currentThread().getName() + "get " + exchanger.exchange(2019));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.print(Thread.currentThread().getName() + "get " + exchanger.exchange(2018));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        Thread t1 = new Thread(r1);
        t1.setName("A");
        Thread t2 = new Thread(r2);
        t2.setName("B");

        t1.start();
        t2.start();

        try {
            Thread.currentThread().sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
