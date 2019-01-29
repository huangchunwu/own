package cn.hcw.designmode;

/**
 * Created by huangchunwu on 2019/1/29.
 */
public class StrategyB implements Strategy {


    @Override
    public void confirmTicket() {

    }

    @Override
    public boolean matching(Integer factor) {
        return false;
    }
}
