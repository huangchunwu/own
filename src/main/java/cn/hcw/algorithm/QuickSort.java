package cn.hcw.algorithm;

/**
 * Created by huangchunwu on 2018/11/6.
 * 快速排序
 */
public class QuickSort extends Algorithm{



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

        int low =0; int high = quickSort.arry.length-1;
        quickSort.sort(quickSort.arry,low,high);

        quickSort.printArray(quickSort.arry);
    }

}
