package cn.hcw.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;


/**
 * 分而治之
 * forkjoin 线程池是双端队列，采用窃取算法
 * Created by huangchunwu on 2019/1/21.
 */
public class ForkJoinTaskTest {


    @Test
    public  void testInvokeForkPool(){
        // 创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();

        long startTime = System.currentTimeMillis();
        ForkJoinTask task = new CountTask(1,999999999);
        Integer result = (Integer) pool.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }


    @Test
    public  void testsubmitForkPool(){
        // 创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();

        long startTime = System.currentTimeMillis();
        ForkJoinTask task = new CountTask(1,999999999);
        pool.submit(task);
        Integer result =0;
        try {
            result = (Integer) task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
    }
}
