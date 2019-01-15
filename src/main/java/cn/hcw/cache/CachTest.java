package cn.hcw.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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


    final static LoadingCache<String, Integer> cache2 = CacheBuilder.newBuilder()
            .maximumSize(10)  //最多存放十个数据
            .expireAfterWrite(10, TimeUnit.SECONDS)  //缓存200秒
            .recordStats()   //开启 记录状态数据功能
            .build(new CacheLoader<String, Integer>() {
                //数据加载，默认返回-1,也可以是查询操作，如从DB查询
                @Override
                public Integer load(String key) throws Exception {
                    return -1;
                }
            });


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
}
