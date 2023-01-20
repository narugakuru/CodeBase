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

// 1. 顺序表查出值为x的结点，用y替换

void replace(LinkList head,int x,int y){
    LinkList p = head;
    while (p && p->data!=x)
        p=p->next;
    if (p)
        p->data=y;
}

// 2. 递归查找二叉树双分支结点个数
int binsearch(BiTree t)
{
    if (t = NULL)
        return 0;

    if (t->lchild && t->rchild)
        return binsearch(t->lchild) + binsearch(t->rchild) + 1;

    else if (t->lchild)
        return binsearch(t->lchild);
        
    else
        return binsearch(t->rchild);
}

// 3. 合并两个带头结点的升序链表味一个新的带头节点的升序链表
LinkList listMerge(LinkList l1, LinkList l2)
{

    LinkList head = (LinkList)malloc(sizeof(LNode));
    head->data = NULL;
    head->next = NULL;
    LinkList l3 = head;

    while (l1 && l2)
    {
        if (l1->data < l2->data)
        {
            l3->next = l1;
            l1 = l1->next;
        }
        else
        {
            l3->next = l2;
            l2 = l2->next;
        }
    }
    while (l1)
    {
        l3->next = l1;
        l1 = l1->next;
    }
    while (l2)
    {
        l3->next = l2;
        l2 = l2->next;
    }

    return head;
}

int main()
{
    return 0;
}