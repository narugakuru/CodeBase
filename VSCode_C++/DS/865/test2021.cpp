#include <stdio.h>
#include <stdlib.h>
#include <iostream>

typedef int datatype;

// 单链表
typedef struct LNode
{
    datatype data;
    LNode *next;
} LNode, *LinkList;

typedef struct BiTNode
{
    datatype data;                   // 数据域
    struct BiTNode *lchild, *rchild; // 左右孩子指针
} BiTNode, *BiTree;

#define MaxSize 100
typedef struct
{
    int data[MaxSize];
    int size;
} list;

// 1. 交叉合并顺序表
list *merge(list a, list b)
{
    int i, j;
    list *c = (list *)malloc(sizeof(list));
    c->size = 0;
    if ((a.size + b.size) > MaxSize)
    {
        printf("表过大，无法合并");
        return NULL;
    }
    i = 0;
    j = 0;
    while (i < (a.size - 1) && j < (b.size - 1))
    {
        if (i <= j)
        {
            c->data[c->size] = a.data[i];
            i++;
        }
        else
        {
            c->data[c->size] = b.data[j];
            j++;
        }
        c->size++;
    }
    while (i < (a.size - 1))
    {
        c->data[c->size++] = a.data[i];
        i++;
    }
    while (j < (b.size - 1))
    {
        c->data[c->size++] = b.data[j];
        j++;
    }

    return c;
}

// 2. 判断带头结点单链表是否为升序，返回1：0

int sortJudge(LinkList l)
{
    if (l->next == NULL)
        return 1;
    LinkList pre, p;
    pre = l->next;
    p = pre->next;
    while ((pre->data < p->data) && p != NULL) // 遍历链表，直至null或者不为升序
    {
        pre = p;
        p = p->next;
    }
    if (p)
        return 0;
    else
        return 1;
}



//函数声明
void InitStack(SqStack &S);//初始化
bool Push(SqStack &S, BiTree t);//入栈
BiTree Pop(SqStack &S);//出栈,并打印出栈顶元素
bool GetTop(SqStack S, BiTree t);//读取栈顶元素
int GetTopOther(SqStack S);//读取栈顶元素的第二种实现方式

// 3. 非递归计算二叉树深度
// 多种思路，层序遍历，需要借助队列操作 。深度优先遍历，需要栈，较为简单
#define MaxSize 10
typedef struct
{
    BiTree data[MaxSize];
    int top;
} SqStack;
int binDeep(BiTree t)
{
    int max = 0, now = 0;
    SqStack s;
    s.top = 0;
    while (t || s.top != 0)
    {
        if(t){
            now++;
            s.data[s.top++] = t;
            t=t->lchild;
        }else{
            now--;
            t=s.data[s.top--];
            t->rchild;
        }
        if (max<now)
        {
            max=now;
        }
    }
    return max;
}

void order(BiTree t){
    if(!t) return;
    // 先序
    order(t->lchild);
    // 中序
    order(t->rchild);
    // 后序
}