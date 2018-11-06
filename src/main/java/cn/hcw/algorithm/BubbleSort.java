package cn.hcw.algorithm;

/**
 * Created by huangchunwu on 2018/11/6.
 * 冒泡排序:
 * 从首位开始，比较相邻的2个，结果是最大的沉在最底下了，第一次时间复杂度是 N
 * 再次从首位开始，比较相邻的2个，除掉最后一个数值不需要比较的，时间复杂度是N-1
 * ..........
 * 最后从首位比较，时间复杂度是1
 *
 */
public class BubbleSort extends Algorithm{





    public  void sort(Integer[] arry){
        if (arry != null && arry.length>0){

            for (int i=0;i<arry.length-1;i++){

                for (int j=0;j<arry.length-i-1;j++){
                    int temp = arry[j];
                    if (arry[j]>arry[j+1]){
                        arry[j] = arry[j+1];
                        arry[j+1] = temp;
                    }
                }
                printArray(arry);

            }
        }

    }




    public static void main(String[] args) {
        BubbleSort bubbleSort =  new BubbleSort();
        bubbleSort.printArray(bubbleSort.arry);
        bubbleSort.sort(bubbleSort.arry);
        bubbleSort.printArray(bubbleSort.arry);
    }

}
