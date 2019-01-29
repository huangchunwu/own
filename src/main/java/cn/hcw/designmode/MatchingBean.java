package cn.hcw.designmode;

/**
 * Created by huangchunwu on 2019/1/29.
 */
public interface MatchingBean<T> {

    boolean matching(T factor);

}
