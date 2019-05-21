package cn.hcw.bitMap;

import java.util.BitSet;

/**
 * 给定一个最多包含40亿个随机排列的32位整数的顺序文件，找出一个不在文件中的32位整数(
 * 在文件中至少缺失这样一个数——为什么？)。在具有足够内存的情况下，如何解决该问题？（编程珠玑）
 */
public class BitMapTest {

    public static void main(String[] args) {
        int [] array = new int [] {1,2,3,22,0};
        BitSet bitSet  = new BitSet(6);
        //将数组内容组bitmap
        for(int i=0;i<array.length;i++)
        {
            bitSet.set(array[i], true);
        }
        System.out.println(bitSet.size());
        System.out.println(bitSet.get(8));
    }
}
