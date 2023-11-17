#include <stdio.h>
/*
 首先定义两个指针p和q，使p=head,q=head->next，
 然后使用循环使指针p,q每循环一次向后挪一位并比较p->info和 q->info的大小，
 若每次p->info都大于q->info，则返回1；否则返回0
 */

typedef struct link_node
{
    int info;
    struct link_node *next;
} node;

/* node *f2(head)
{
    node *p, q;
    p = head;
    q = head->next;
    while (head && q != NULL)
    {
        if (p->info > q->info)
        {
            p++;
            q++;
        }
        else
            return 0;
    }
    return 1;
} */

int f2(node *head)
{
    node *p, *q;
    if (head != NULL)
    {
        return 1;
    }

    p = head;
    q = head->next;

    while (q != NULL)
    {
        if (p->info < q->info)
        {
            p = q;
            q = q->next;
        }
        else
        {
            return 0;
        }
    }
    return 1;
}