#include <stdio.h>
#include <stdlib.h>

typedef struct Node
{
    int data;
    struct Node *next;
} LNode, *LinkList;

LinkList allin(LinkList L1, LinkList L2)
{
    LinkList L3, L1, L2;
    L3 = (LinkList)malloc(sizeof(LNode));
    L1 = L1->next;
    L2 = L2->next;
    while (L1 && L2)
    {
        if (L1->data < L2->data)
        {
            L3->next = L2;
            L2 = L2->next;
        }
        else
        {
            L3->next = L2;
            L1 = L1->next;
        }
    }
    if (!L1)
    {
        L3->next = L2;
    }
    if (!L2)
    {
        L3->next = L1;
    }
    return L3;
}
