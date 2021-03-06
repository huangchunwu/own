package cn.hcw.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * expireAfterWrite KEY超时的时候，guava会移除key，get请求的时候会堵塞线程，通过load（）加载新值
 * refreshAfterWrite KEY到了设置的刷新时间，guava不会移除key，get请求的时候只会挑选一个线程去执行reload（）加载新值，其他线程则返回旧值
 * Created by Administrator on 2019/1/15 0015.
 */
public class CachTest {
    //Cache初始化：
    final static Cache<Integer, String> cache = CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值
            .initialCapacity(10)
                    //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作
            .concurrencyLevel(5)
                    //设置cache中的数据在写入之后的存活时间为10秒
            .expireAfterWrite(10, TimeUnit.SECONDS)
                    //构建cache实例
            .build();

    //LoadingCache 初始化
    final static LoadingCache<String, Integer> loadCache = CacheBuilder.newBuilder()
            .maximumSize(10)  //最多存放十个数据
            .expireAfterWrite(4, TimeUnit.SECONDS)  //缓存4秒
            .refreshAfterWrite(2, TimeUnit.SECONDS) //刷新频率2S
            .recordStats()   //开启 记录状态数据功能
            .build(new CacheLoader<String, Integer>() {
                //数据加载，默认返回-1,也可以是查询操作，如从DB查询
                @Override
                public Integer load(String key) throws Exception {
                    System.out.println(Thread.currentThread().getName()+">>>>>key:"+key+">>>>>>load****");
                    return -1;
                }
                @Override
                public ListenableFuture<Integer> reload(String key, Integer oldValue) throws Exception {
                    System.out.println(Thread.currentThread().getName()+"......后台线程池异步刷新:" + key);
                    return service.submit(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            System.out.println(Thread.currentThread().getName()+">>>> refresh");
                            return 99;
                        }
                    });
                }

            });

    // guava线程池,用来产生ListenableFuture
    private static ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


    @Test
    public  void  testCallable(){
        cache.put(1,"上海");


        System.out.println( cache.getIfPresent(0));
        try {
            String result = cache.get(2, new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return null;//load DB 不能为空
                }
            });

            System.out.println(result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }



    @Test
    public  void  testLoadCache(){
        loadCache.put("1",1);
        loadCache.put("key1",233);
        int time = 1;
        while(true) {
            System.out.println("第" + time++ + "次取到key1的值为：" + loadCache.getIfPresent("key1"));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(cache.stats()); //获取统计信息
        }

    }

    @Test
    public void testRefreshAndExpire(){
        loadCache.put("ssa",1212);

        try {
            Thread.currentThread().sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() +">>>get:"+loadCache.get("ssa"));
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        try {
            Thread.currentThread().sleep(1000 * 30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
