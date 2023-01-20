#include <stdio.h>
#include <time.h>
#include <stdlib.h>

//声明节点结构
typedef struct Linklist
{
    int elem;          //存储整形元素
    struct Link *next; //指向直接后继元素的指针
} link;

//创建链表的函数
link *createLink(int n)
{

    srand(time(0));                         //初始化随机数
    link *p = (link *)malloc(sizeof(link)); //创建一个头结点
    link *temp = p;                         //声明一个指针指向头结点，用于遍历链表

    //生成链表,尾插法
    for (int i = 0; i < n; i++)
    {
        //创建节点并初始化
        link *a = (link *)malloc(sizeof(link));
        a->elem = rand() % 100 + 1;
        a->next = NULL;
        //建立新节点与直接前驱节点的逻辑关系
        temp->next = a;
        temp = temp->next;
    }

    return p;
}

//遍历输出链表
void getLink(link *l)
{

    while (l)
    {
        printf("%d ", l->elem);
        l = l->next;
    }
}

//在坐标index插入数据e
char *insertLink(link *l, int index, int e)
{
    int j = 1;
    link *p, *s;
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

    s = (link *)malloc(sizeof(link));
    //插入操作
    s->elem = e;
    s->next = p->next;
    p->next = s;
    return "OK";
}

//删除链表第index个结点
char *deleteLink(link *l, int index, int *e)
{
    int j = 1;
    link *p, *q;
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
    q = p->next;

    p->next = q->next;
    *e = q->next;
    free(q);

    return "OK";
}
//无尾指针，删除链表尾结点。
char *deleteLinkLast(link *l, int index, int *e)
{
    if (l->next==NULL)
    {
        return "false";
    }
    
    link *p, *q;
    p = l;
    while (p->next != NULL)
    {
        q = p;
        p = p->next;
    }
    q->next = NULL;
    return "true";
}

int main()
{
    link *createLink(int n);
    void getLink(link * l);
    char *insertLink(link * l, int index, int e);
    char *deleteLink(link * l, int index, int *e);

    link *l = createLink(10);

    getLink(l);
    printf("\n");

    insertLink(l, 1, 1000);

    getLink(l);
    printf("\n");

    int *a;
    deleteLink(l, 11, a);
    printf("%d\n", a);

    getLink(l);
    printf("\n");

    system("pause");
    return 0;
}