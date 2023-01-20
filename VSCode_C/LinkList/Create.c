#include <stdio.h>
#include <time.h>
#include <stdlib.h>

//声明节点结构
typedef struct Link
{
    int elem;          //存储整形元素
    struct Link *next; //指向直接后继元素的指针
} link;
//创建链表，返回長度為length的链表
link *initLink(int length)
{
    srand(time(0));
    link *p = (link *)malloc(sizeof(link)); //创建一个头结点
    link *temp = p;                         //声明一个指针指向头结点，用于遍历链表

    //生成链表,填充随机数
    for (int i = 0; i < length; i++)
    {
        //创建节点并初始化
        link *a = (link *)malloc(sizeof(link));
        a->elem = rand() % 100 + 1; // 1~100的随机数
        a->next = NULL;
        //建立新节点与直接前驱节点的逻辑关系
        temp->next = a;
        temp = temp->next;
    }
    return p;
}

int main()
{
    // link *initLink(); //声明

    link *l = initLink(5);

    l = l->next;
    while (l)
    {
        printf("%d \t", l->elem);
        l = l->next;
    }

    system("pause");
    return 0;
}