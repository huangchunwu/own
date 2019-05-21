package cn.hcw.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Cutodown {




    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        CountDownLatch countDownLatch = new CountDownLatch(2);

        List<Callable<String>> exexute = new ArrayList<>();
        for (int i =0 ;i<10;i++){
            Callable<String> callable =   new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "a";
                }
            };

            exexute.add(callable);
        }




        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<String>>  futures =  executorService.invokeAll(exexute);


        for(Future<String> f :  futures){
            System.out.println(f.get(60,TimeUnit.SECONDS));
        }



    }
}
