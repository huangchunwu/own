package cn.hcw.algorithm;

/**
 * Created by huangchunwu on 2018/11/6.
 * 选择排序
 * 假设i=0,为最小值，与n-i个元素比较，找出最小的值，与i=0 交换位置，则保证i=0,是整个数组最小值
 * 再假设i=1为最小值，与n-2个元素比较，找出最小值，与i=1交换位置，保证i=1,是剩下数组最小值
 */
public class SelectSort extends Algorithm{


    @Override
    public void sort(Integer[] array){

        for (int i=0;i<array.length;i++){

            int min = array[i];
            int minIndex = i;

            //[i,n-i] 找出最小值
            for (int j=i+1;j<array.length;j++){

                if (array[j]<  min){
                    min = array[j];
                    minIndex = j;
                }
            }
            //交换最小值的位置与i位置
            if (minIndex != i){
                swap(array,i,minIndex);
            }

        }
    }


    public static void main(String[] args) {
        SelectSort selectSort =  new SelectSort();
        selectSort.printArray(selectSort.arry);
        selectSort.sort(selectSort.arry);
        selectSort.printArray(selectSort.arry);
    }

}
