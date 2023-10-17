#include <stdlib.h>
#include <stdio.h>
#include <time.h>

typedef struct DoubleNode
{
    double data;
    struct DoubleNode *prior;
    struct DoubleNode *next;
} Dulink;

Dulink *createDulink(int n)
{
    srand(time(0));                               //初始化随机数
    Dulink *p = (Dulink *)malloc(sizeof(Dulink)); //创建一个头结点
    Dulink *temp = p;                             //声明一个指针指向头结点，用于遍历链表

    //生成链表,尾插法
    for (int i = 0; i < n; i++)
    {
        //创建节点并初始化
        Dulink *a = (Dulink *)malloc(sizeof(Dulink));
        a->data = rand() % 100 + 1;
        a->next = NULL;
        //建立新节点与直接前驱节点的逻辑关系
        temp->next = a;
        a->prior = temp;

        temp = temp->next;
    }

    return p;
}

void getLink(Dulink *l)
{
    l = l->next;

    while (l)
    {
        printf("%.0lf ", l->data);
        l = l->next;
    }
}

//在坐标index插入数据e
char *insertLink(Dulink *l, int index, int e)
{
    int j = 1;
    Dulink *p, *s;
    p = l;
    while (p && j < index) //遍历寻找第i-1个结点
    {
        p = p->next;
        j++;
    }
    if (!p || j > index)
    {
        return "ERROR"; //第i个结点不存在
    }

    s = (Dulink *)malloc(sizeof(Dulink));
    //插入操作
    s->data = e;
    /*     s->next = p->next;
    p->next = s; */
    s->prior = p;
    s->next = p->next;
    p->next->prior = s;
    p->next = s;

    return "OK";
}

//删除链表结点
char *deleteLink(Dulink *l, int index, int *e)
{
    int j = 1;
    Dulink *p, *q;
    p = l;

    while (p->next && j < index) //遍历寻找第i-1个结点
    {
        p = p->next;
        j++;
    }

    if (!(p->next) || j > index)
    {
        return "error";
    }

    //删除操作
    q = p->next; //q为被删除结点

    q->prior->next = q->next;
    q->next->prior = q->prior;

    *e = q->data;
    free(q);

    return "OK";
}

void main()
{

    Dulink *l = createDulink(10);

    getLink(l);
    printf("\n");

    int *a;
    deleteLink(l, 1, a);
    printf("%d\n",*a);

    getLink(l);
    printf("\n");

    system("pause");
}