package cn.hcw.designmode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangchunwu on 2019/1/29.
 */
public class Context {


    FactoryList<Strategy,Integer> factoryList;

    protected static ThreadLocal<Map<String, Context>> contextThreadLocal = new ThreadLocal();


    public static Context getActionContext(String actionName) {
        Map map = getActionContextMap(contextThreadLocal);
        return (Context)map.get(actionName);
    }

    protected static <T> Map<String, Context> getActionContextMap(ThreadLocal<Map<String, Context>> threadLocal) {
        Map map = (Map)threadLocal.get();
        if(map == null) {
            map = new HashMap();
            threadLocal.set(map);
        }

        return (Map)map;
    }

    public static void release(String actionName) {
        clearActionContext(actionName);
    }

    public static void clearActionContext(String actionName) {
        Map actionContextMap = (Map)contextThreadLocal.get();
        if(actionContextMap != null) {
            actionContextMap.remove(actionName);
            if(actionContextMap.isEmpty()) {
                contextThreadLocal.remove();
            }
        }

    }



    @Test
    public void run(){

        Context context = getActionContext("act");

        try {
            factoryList.getBean(1).confirmTicket();


        }catch (Exception e){

        }finally {
            Context.release("act");
        }


    }
}
