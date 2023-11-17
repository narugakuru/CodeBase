#include <stdio.h>
#define MAXSIZE 100
typedef struct
{
    int a[MAXSIZE];
    int size;
} list;
/*
 先用循环把顺序表A，B交叉放进顺序表C中，
 然后当m<=n，且当前存入的个数i>m时，用循环继续把顺序表B 中剩下的数放进顺序表C中，
 当m>n，且当前存入的个数i>n时，使用循环将顺序表A中剩下的数存放入顺序表C中。
 */

list *merge0(list *A, list *B)
{
    int m = A->size;
    int n = B->size;
    list *C = (list *)malloc(sizeof(list));
    int j, k = 0;
    while (m <= n)
    {
        for (int i = 0; i < m + n; i++)
        {
            if (i > m)
            {
                C->a[i] = B->a[k];
                k++;
            }
            else
            {
                C->a[i] = A->a[j];
                C->a[i + 1] = B->a[k];
                j++;
                k++;
                i++;
            }
        }
    }
    while (m > n)
    {
        for (int i = 0; i < m + n; i++)
        {
            if (i > n)
            {
                C->a[i] = A->a[j];
                j++;
            }
            else
            {
                C->a[i] = A->a[j];
                C->a[i + 1] = B->a[k];
                j++;
                k++;
                i++;
            }
        }
    }
}

list *merge(list *A, list *B)
{
    int m = A->size;
    int n = B->size;
    list *C = (list *)malloc(sizeof(list));
    int j, k = 0;
    // B更长
    while (m <= n)
    {
        for (int i = 0; i < m + n;)
        {
            if (i > m)
            {
                C->a[i++] = B->a[k++];
            }
            else
            {
                C->a[i++] = A->a[j++];
                C->a[i++] = B->a[k++];
            }
        }
    }
    while (m > n)
    {
        for (int i = 0; i < m + n;)
        {
            if (i > n)
            {
                C->a[i++] = A->a[j++];
            }
            else
            {
                C->a[i++] = A->a[j++];
                C->a[i++] = B->a[k++];
            }
        }
    }
}

list *merge2(list *A, list *B)
{
    int m = A->size;
    int n = B->size;
    list *C = (list *)malloc(sizeof(list));
    C->size = m + n;

    int i = 0, j = 0, k = 0;
    // 拼接AB到C
    while (j < n && i < m)
    {
        C->a[i++] = A->a[j++];
        C->a[i++] = B->a[k++];
    }

    while (i < (m + n))
    {
        if (m > n) // A更长
        {
            C->a[i++] = A->a[j++];
        }
        else // B更长
        {
            C->a[i++] = B->a[k++];
        }
    }

    return C;
}