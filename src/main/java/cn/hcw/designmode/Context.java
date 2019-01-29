package cn.hcw.designmode;

/**
 * Created by huangchunwu on 2019/1/29.
 */
public class Context {


    FactoryList<Strategy,Integer> factoryList;


    public void ask(){

        factoryList.getBean(1).confirmTicket();

    }
}
