#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAXSIZE 100

typedef struct Sqlist
{
    int data;
    int cur; //游标，为0时指向*
} staticLinkList[MAXSIZE];

//space[0].data里存放的是第一个备用空闲下标
//初始化静态链表
static char * InitList(staticLinkList space[MAXSIZE])
{
    int i;
    srand(time(0)); //生成随机数种
    for (i = 0; i < (MAXSIZE - 1); i++)
    {
        space[i]->cur = i + 1;
        space[i]->data = rand() % 100 + 1; //赋予随机数
    }
    space[MAXSIZE - 1]->cur = 0;

    return "OK";
}

//获取空闲空间的下标
int MallocNode(staticLinkList space)
{
    int i = space[0].cur;
    //返回第一个备用空闲的下标
    if (space[0].cur) //由于第一个空闲下标被使用了，把第二个空闲下标给space[0]
        space[0].cur = space[i].cur;

    return i;
}


int GetStaticLink(staticLinkList l[MAXSIZE])
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
    char * InitList(staticLinkList space[MAXSIZE]);
    staticLinkList *l;
    InitList(l);

    GetStaticLink(l);

    system("pause");
    return 0;
}
