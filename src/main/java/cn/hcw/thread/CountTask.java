package cn.hcw.thread;


import java.util.concurrent.RecursiveTask;

/**
 *
 * Created by huangchunwu on 2019/1/25.
 */
public class CountTask extends RecursiveTask<Integer> {


    /**
     * 临界值
     */
    private static final int THRESHOLD = 1000;

    int start=0;
    int end=100000;
    int sum = 0;

    public CountTask(int start,int end){
        this.start = start;
        this.end = end;
    }



    @Override
    protected Integer compute() {

        if (end-start<THRESHOLD){
            for (int i=start;i<end;i++){
                sum += i;
            }
        }else {
            int middle = (start + end)/2;

            RecursiveTask<Integer> left_task =  new CountTask(start,middle);
            RecursiveTask<Integer> right_task =  new CountTask(middle,end);

            invokeAll(left_task,right_task);

            sum = left_task.join() + right_task.join();
        }
        return sum;
    }
}
