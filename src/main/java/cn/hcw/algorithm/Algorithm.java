package cn.hcw.algorithm;

/**
 * Created by huangchunwu on 2018/11/6.
 */
public class Algorithm {

    Integer[] arry = {121, 4, 432, 1, 342, 2, 523, 23, 423, 23, 422};


    /**
     * 数组的 i,j位置交换
     * @param arry
     * @param i
     * @param j
     */
    public void swap(Integer[] arry, int i, int j) {
        int temp = arry[i];
        arry[i] = arry[j];
        arry[j] = temp;

    }


    public void printArray(Integer[] arry) {
        if (arry != null && arry.length > 0) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            for (int i = 0; i < arry.length; i++) {
                System.out.print(arry[i]);
                System.out.print(",");
            }
            System.out.println();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }

}
