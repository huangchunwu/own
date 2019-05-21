package cn.hcw.juc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedEx {

    static ConcurrentHashMap<String, String> cachMap = new ConcurrentHashMap<String, String>();

    static ReentrantLock lock = new ReentrantLock();

    public static  String getKey(String key) {


        if (!cachMap.containsKey(key)) {
            synchronized (cachMap) {
                if (!cachMap.containsKey(key)){
                    // load data from db
                    System.out.println("load data from db");
                    cachMap.put("abc","ok");
                }
            }
        }

        return cachMap.get(key);
    }

    public static String getLockKey(String key){


        if(!cachMap.containsKey(key)){
            lock.lock();
            if (!cachMap.containsKey("key")){
                System.out.println("load data from db");
                cachMap.put("abc","ok");
            }
            lock.unlock();
        }
        return cachMap.get(key);
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getLockKey("abc");
                }
            }).start();
        }

    }

}
