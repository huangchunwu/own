package cn.hcw.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockEx implements Runnable{

    private final ReentrantLock mainLock = new ReentrantLock();

    volatile Integer sum = 0;

    public void incream(){
        mainLock.lock();
        Condition c =  mainLock.newCondition();
        try {
            sum++;
           // c.await(30, TimeUnit.SECONDS);
            c.await();
        }catch (Exception e) {

        }finally {
            mainLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(new LockEx()).start();

        new Thread(new LockEx()).start();
    }

    @Override
    public void run() {
        incream();
    }
}
