#include <stdio.h>
#include <stdlib.h>

// **1. 带头结点的链表实现插入排序**
// 2. 查找前序遍历的最后一个结点，要求分别递归和非递归实现
// 3. 使用邻接表存储的aov有向图，输出入度为0的顶点。
typedef int datatype;

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

// test1 ：带头结点的链表实现插入排序
// 1,p,next指向原表，newp，pre指向新表
// 2,比较新表结点newp和原表p结点的值，找到p的插入位置
// 3，将p插入新表
// 4，p指向next，next指向next的后继结点，继续下一轮比较插入
void insertSort(LinkList head)
{

    LinkList p, next, new_p, new_pre; // p,next指向原表，newp，pre指向新表

    next = head->next;
    head->next = NULL; // 将原表划分为两种表

    while (p)
    {
        // 指向原表下一个结点
        p = next;
        next = p->next;
        // 新表从头开始遍历
        new_pre = head;
        new_p = head->next;

        while (new_p && new_p->data < p->data) // 找到p在新表的插入位置
        {
            new_pre = new_p;
            new_p = new_p->next;
        }
        // 将p插入指新表的new_pre后面
        p->next = new_p;
        new_pre->next = p;
    }
}

// test2 查找前序遍历的最后一个结点，要求分别递归和非递归实现
// 非递归，1，若p空，直接返回。
// 2，只要存在左右孩子就一直循环，优先指向右孩子，当没有右孩子再指向左孩子。
BiTree beforelast(BiTree t)
{
    BiTree p = t;
    if (!p)
        return p;

    while (p->lchild || p->rchild)
    {
        if (p->rchild)
        {
            p = p->rchild;
        }
        else
        {
            p = p->lchild;
        }
    }

    return p;
}

// 递归

BiTree beforelast2(BiTree t)
{
    if (!t) // 当
    {
        return t;
    }
    if (t->lchild == NULL && t->rchild == NULL) // 若t无左右孩子，终止递归
    {
        return t;
    }

    if (t->rchild) // 优先指向右孩子，无右孩子指向左孩子
    {
        beforelast2(t->rchild);
    }
    else
    {
        beforelast2(t->lchild);
    }
}

#define MaxVertexNum 9

//"边"/"弧"
typedef struct enode
{
    int adjvex;         // 边/弧指向哪个结点
    struct enode *next; // 指向下一条弧的指针
    // InfoType info;  //边权值
} EdgeNode;

//"顶点"
typedef struct vnode
{
    int Vertex;       // 顶点信息，数据类型不定，int只是一个例子
    enode *firstEdge; // 第一条边/弧
} VNode;

// 用邻接表存储的图
typedef struct
{
    VNode adjlist[MaxVertexNum];
    int n, e; // 顶点n，边数e
} LinkedGraph;

// test3 使用邻接表存储的aov有向图，输出入度为0的顶点。
// 使用数组遍历统计所有顶点的入度

void adjlistPrint(LinkedGraph g)
{
    int sum[MaxVertexNum];
    for (int i = 0; i < MaxVertexNum - 1; i++) // 初始化数组
    {
        sum[i] = 0;
    }

    for (int i = 0; i < MaxVertexNum - 1; i++) // 对每个顶点遍历
    {
        EdgeNode* p = g.adjlist[i].firstEdge;
        while (p) // 统计这个顶点对其他顶点的入度
        {
            sum[p->adjvex]++;
            p = p->next;
        }
    }

    for (int i = 0; i < MaxVertexNum - 1; i++)//打印入度为零的顶点
    {
        if (sum[i] == 0)
            printf("出度为零的顶点是%d", i);
    }
}

int main()
{

    return 0;
}