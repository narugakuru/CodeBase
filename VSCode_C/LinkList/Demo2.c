#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAXSIZE 100

typedef struct Sqlist
{
    int data;
    int cur; //游标，为0时指向*
} slList[MAXSIZE];

//space[0].data里存放的是第一个备用空闲下标
//初始化静态链表
char *initList(slList *space)
{
    int i = 0;
    srand(time(0)); //生成随机数种
    space = NULL;
    for (i = 0; i < (MAXSIZE - 1); i++)
    {
        space[i]->cur = i + 1;
        space[i]->data = rand() % 100 + 1; //赋予随机数
    }
    space[MAXSIZE - 1]->cur = 0;

    return "OK";
}

int GetStaticLink(slList *l)
{
    int j = 0;
    int i = l[MAXSIZE - 1]->cur;

    while (i)
    {
        printf("%d", l[i]->data);
        i = l[i]->cur;

        j++;
    }
    return j;
}

int main()
{

    slList l[MAXSIZE];
    // InitList(l);
    initList(l);

    GetStaticLink(l);

    system("pause");
    return 0;
}