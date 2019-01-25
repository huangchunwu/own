package cn.hcw.thread;

import java.util.concurrent.RecursiveAction;

/**
 * RecusiveAction  没有返回值的
 * RecusiveTask  有返回值
 * Created by huangchunwu on 2019/1/24.
 */
public class PrintTask extends RecursiveAction {
    @Override
    protected void compute() {
        System.out.println("=====");
    }
}
