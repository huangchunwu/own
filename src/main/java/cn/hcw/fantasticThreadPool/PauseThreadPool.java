package cn.hcw.fantasticThreadPool;

import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PauseThreadPool extends ThreadPoolExecutor {

    private  volatile boolean    isPaused;

    private ReentrantLock pauseLock = new ReentrantLock();
    private Condition unpaused = pauseLock.newCondition();

    public PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    //线程池执行任务之前
    protected void beforeExecute(Thread t, Runnable r) {
        pauseLock.lock();
        try {
            while (isPaused) {
                unpaused.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pauseLock.unlock();
        }


    }


    public void pause() {
        isPaused = true;
    }

    public void resume() {
        pauseLock.lock();
        try {
            isPaused = false;
            unpaused.signalAll();
        }finally {
            pauseLock.unlock();
        }
    }

    //线程池执行任务之前
    protected void afterExecute(Runnable r, Throwable t) {
        System.out.println("afterExecute  task:" + Thread.currentThread().getName());
    }

    //线程池终止时候调用
    protected void terminated() {
        System.out.println("terminated  task:" + Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        PauseThreadPool pool = new PauseThreadPool(5, 10, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10));
        try {
            System.out.print("Enter a char :");
            while (true) {
                Scanner sc = new Scanner(System.in);
                int val = sc.nextInt();
                if (val == 13 || val == 10) {

                }
                if (val == 1) {
                    pool.pause();
                } else if (val == 2) {
                    pool.resume();
                } else if (val == 0) {
                    pool.submit(new Callable<Object>() {
                        @Override
                        public Object call() throws Exception {
                            System.out.println("start  task:" + Thread.currentThread().getName());
                            return null;
                        }
                    });
                }else if (val == 3){
                    pool.shutdown();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
