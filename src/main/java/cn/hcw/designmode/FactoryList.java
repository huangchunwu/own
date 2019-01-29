package cn.hcw.designmode;

import java.util.List;

/**
 * Created by huangchunwu on 2019/1/29.
 */
public interface FactoryList<E extends MatchingBean<K>, K> extends List<E> {

    E getBean(K factor);

}
