package juniorSort;

import com.sun.istack.internal.NotNull;
import org.junit.jupiter.api.Test;

public class Sort插入希尔选择 {

/*    public static void main(String[] args) {
        int[] array = {5, 64, 61, 86, 8, 13, 75, 25};
        selectSort(array);
        insertionSort(array);
    }*/

    @Test
    void main(){
        int[] array = {5, 64, 61, 86, 8, 13, 75, 25};
        selectSort(array);
        insertionSort(array);
    }

    //插入排序√
    public static void insertionSort(int[] array) {
        if (null == array || 1 == array.length) {
            return;
        }//若数组长度小于1则直接结束
        for (int i = 1; i < array.length; i++) {
            int temp;
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    //在暂存区从右往左进行比较，更大则后移一位，否则插入
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        allPrint(array);
    }

/*
* 先将整个待排元素序列分割成若干个子序列（由相隔某个“增量”的元素组成的）
* 分别进行直接插入排序，然后依次缩减增量再进行排序，待整个序列中的元素基本有序（增量足够小）时，
* 再对全体元素进行一次直接插入排序。因为直接插入排序在元素基本有序的情况下（接近最好情况），效率是很高的。*/

    //希尔排序,进行预处理的插入排序
    static void ShellSort(int[] arr, int n) {
        int gap = n;
        while (gap > 1) {//第一个循环决定比较间隔
            //每次对gap折半操作
            gap = gap / 2;
            //单趟排序
            for (int i = 0; i < n - gap; ++i) {//第二个循环根据间隔，将数组分成若干个子序列
                int end = i;
                int tem = arr[end + gap];
                while (end >= 0) {//第三个循环对子序列进行插入排序算法
                    if (tem < arr[end]) {
                        arr[end + gap] = arr[end];
                        end -= gap;
                    } else {
                        break;
                    }
                }
                arr[end + gap] = tem;
            }
        }
    }

    //选择排序√
    //找一个最小值和第i个值置换
    public static void selectSort(int[] array) {
        if (null == array || 1 == array.length) {
            return;
        }
//        int[] array = new int[10];
        for (int i = 0; i < array.length; i++) {

            int min = i;//min为需要置换的最小值

            for (int j = i; j < array.length; j++) {//寻找最小值下标
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            //置换
            int tmp = array[i];
            array[i] = array[min];
            array[min] = tmp;
        }
        allPrint(array);
    }

    public static void allPrint(@NotNull int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
