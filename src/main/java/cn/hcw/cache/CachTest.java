package cn.hcw.cache;

import cn.hcw.util.DateUtils;
import com.google.common.cache.*;
import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
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


    static ListeningExecutorService executorService =
            MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());

    // remove listener
    static RemovalListener<String, Integer> removalListener = new RemovalListener<String, Integer>() {
        public void onRemoval(RemovalNotification<String, Integer> removal) {
            System.out.println(DateUtils.time() +"cause:" + removal.getCause() + " key:" + removal.getKey() + " value:"
                    + removal.getValue());
        }
    };

    final static LoadingCache<String, Integer> loadingCache = CacheBuilder.newBuilder()
            .maximumSize(10)  //最多存放十个数据
                    // .expireAfterWrite(3, TimeUnit.SECONDS)  //缓存200秒
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            .removalListener(removalListener)
            .recordStats()   //开启 记录状态数据功能
            .build(new CacheLoader<String, Integer>() {
                //数据加载，默认返回-1,也可以是查询操作，如从DB查询
                @Override
                public Integer load(String key) throws Exception {
                    System.out.println(DateUtils.time() + ">>>>>>>>>>>>>>>>load>>>>>>>>>>>>>>>>");
                    return -1;
                }

                //有些键不需要刷新，并且我们希望刷新是异步完成的
                @Override
                public ListenableFuture<Integer> reload(final String key, final Integer oldValue) {
                    System.out.println(DateUtils.time() + ">>>>>>>>>>>>>>>>reload>>>>>>>>>>>>>>>>");
                    // we need to load new values asynchronously, so that calls to read values from the cache don't block
                    ListenableFuture<Integer> listenableFuture = executorService.submit(new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            try {
                                Integer value = load(key);
                                return value;
                            } catch (Exception ex) {
                                return oldValue;
                            } finally {
                            }
                        }
                    });
                    return listenableFuture;
                }
            });


    @Test
    public void testCallable() {
        cache.put(1, "上海");


        System.out.println(cache.getIfPresent(0));
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
    public void testLoading() {
        loadingCache.put("1", 1);
        System.out.println(DateUtils.time() + ">>>>>>>>>>>>>>>>put>>>>>>>>>>>>>>>>");
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(DateUtils.time() + ">>>>>>>>>>>>>>>>get>>>>>>>>>>>>>>>>");
            loadingCache.get("1");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        int i = 0;
        while (true) {
            try {
                //System.out.println("第" + (i++) + "次");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
