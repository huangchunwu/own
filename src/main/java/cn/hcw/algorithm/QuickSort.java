package cn.hcw.algorithm;

/**
 * Created by huangchunwu on 2018/11/6.
 * 快速排序
 *
 * 设置一个基准线，数组第一个数字，将其分成小于基准线  和 大于基准线 二块，再分别对2块执行通用的算法。
 *
 * 数组最后一位倒推 ，跟基准线比较，如果小于基准线，则将其换到基准线的位置
 * 再从基准线的位置，开始顺推跟基准线值比较，如果大于基本线，则将其换到高位的位置。
 */
public class QuickSort extends Algorithm{


    @Override
    public void sort(Integer[] array) {
        int low =0; int high = arry.length-1;
        this.sort(array, low, high);
    }

    public Integer partition(Integer[] array,int low,int high){

        int base = array[low];

       while (low<high){
           while (low<high &&array[high]>=base){
                   high--;
           }
           array[low] = array[high];

           while (low<high && array[low]<=base){
               low ++;
           }
           array[high] = array[low];
       }
        array[low] = base;


        return low;

    }

    public void sort(Integer[] array,int low,int high){

        if(low>=high){
            return ;
        }

        int baseIndex =  partition(array, low, high);
        //分为治之
        sort(array, low, baseIndex - 1);
        sort(array, baseIndex + 1, high);
    }


    public static void main(String[] args) {
        QuickSort quickSort =  new QuickSort();
        quickSort.printArray(quickSort.arry);
        quickSort.sort(quickSort.arry);

        quickSort.printArray(quickSort.arry);
    }

}
