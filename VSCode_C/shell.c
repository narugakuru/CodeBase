#include <stdio.h>

void shellSort(int arr[], int n)
{
    // 使用Knuth序列来确定间隔
    int gap = 1;
    while (gap < n / 3)
    {
        gap = gap * 3 + 1;
    }

    while (gap > 0)
    {
        // 对每个间隔进行插入排序
        for (int i = gap; i < n; i++)
        {
            int temp = arr[i];
            int j = i;

            while (j >= gap && arr[j - gap] > temp)
            {
                arr[j] = arr[j - gap];
                j -= gap;
            }

            arr[j] = temp;
        }

        // 缩小间隔
        gap = (gap - 1) / 3;
    }
}

int main()
{
    int arr[] = {9, 5, 7, 2, 1, 8, 3};
    int n = sizeof(arr) / sizeof(arr[0]);

    printf("原始数组：");
    for (int i = 0; i < n; i++)
    {
        printf("%d ", arr[i]);
    }

    shellSort(arr, n);

    printf("\n排序后数组：");
    for (int i = 0; i < n; i++)
    {
        printf("%d ", arr[i]);
    }

    return 0;
}

void printArray(int array[], int length)
{
    for (int i = 0; i < length; i++)
    {
        printf("%d ", array[i]);
    }
    printf("\n");
}

void shellSort2(int array[], int length, int step)
{
    for (int i = 0; i < length; i++)
    {
        for (int j = i + step; j < length; j += step)
        {
            for (int k = i; k < j; k += step)
            {
                if (array[j] < array[k])
                {
                    int temp = array[j];
                    for (int l = j - step; l >= k; l -= step)
                    {
                        array[l + step] = array[l];
                    }
                    array[k] = temp;
                }
            }
        }
    }
}
