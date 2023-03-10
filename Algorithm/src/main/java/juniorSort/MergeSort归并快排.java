package juniorSort;

import org.junit.jupiter.api.Test;

/*
 * 归并排序是一种稳定的排序算法，归并排序的主要问题在于它需要一个与待排序数组一样大的辅助数组空间。
 * 由于归并排序每次划分时两个子序列的长度基本一样，所以归并排序最好、最差和平均时间复杂度都是nlog2n。*/
public class MergeSort归并快排 {
    //两路归并算法，两个排好序的子序列合并为一个子序列
    public void merge(int[] a, int left, int mid, int right) {
        int[] tmp = new int[a.length];//辅助数组
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放辅助数组指针

        while (p1 <= mid && p2 <= right) {
            if (a[p1] <= a[p2])
                tmp[k++] = a[p1++];
            else
                tmp[k++] = a[p2++];
        }
        while (p1 <= mid) tmp[k++] = a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) tmp[k++] = a[p2++];//同上

        //复制回原素组
        if (right + 1 - left >= 0) System.arraycopy(tmp, left, a, left, right - left + 1);
//        for (int i = left; i <=right; i++)
//            a[i]=tmp[i];

    }

    public void mergeSort(int[] a, int start, int end) {
        if (start < end) {//当子序列中只有一个元素时结束递归
            int mid = (start + end) / 2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid + 1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }
    }

    @Test
    public void testMerge() {
        int[] array = {49, 38, 65, 97, 76, 13, 27, 50};
        mergeSort(array, 0, array.length - 1);
        System.out.println("排好序的数组：");
        for (int e : array)
            System.out.print(e + " ");
    }

    @Test
    public void test_Quick_Sort(){
        int[] array = {49, 38, 65, 97, 76, 13, 27, 50};
        Quick_Sort(array, 0, array.length - 1);
        System.out.println("排好序的数组：");
        for (int e : array)
            System.out.print(e + " ");
    }

    //快速排序
    static void Quick_Sort(int[] arr, int begin, int end){
        if(begin > end)
            return;
        int tmp = arr[begin];
        int i = begin;
        int j = end;
        while(i != j){
            while(arr[j] >= tmp && j > i)
                j--;
            while(arr[i] <= tmp && j > i)
                i++;
            if(j > i){
                int t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[begin] = arr[i];
        arr[i] = tmp;
        Quick_Sort(arr, begin, i-1);
        Quick_Sort(arr, i+1, end);
    }
}