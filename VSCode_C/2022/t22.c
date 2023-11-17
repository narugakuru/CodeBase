#include <stdio.h>
#include <stdlib.h>
typedef char datatype;
typedef struct node
{
    datatype data;
    struct node *lchild, *rchild;
} laynode;
typedef laynode *laytree;
// laytree root;
// 2. 递归查找二叉树双分支结点个数

/* int num(laytree l)
{
    int temp;
    if (l != NULL)
        return 0;

    for (int i = 0; i < num(l->lchild) + num(l->rchild) + 1; i++)
    {
        if (l->lchild && l->rchild)
        {
            temp++;
        }
    }

    return temp;
} */

int count(laytree root)
{
    if (root == NULL)
    {
        return 0;
    }
    else if (root->lchild != NULL && root->rchild != NULL)
    {
        return 1 + count(root->lchild) + count(root->rchild);
    }
    else
    {
        return count(root->lchild) + count(root->rchild);
    }
}

int main()
{
    // 构建二叉树
    laytree root = (laytree)malloc(sizeof(laynode));
    root->data = 'A';

    root->lchild = (laytree)malloc(sizeof(laynode));
    root->lchild->data = 'B';
    root->lchild->lchild = NULL;
    root->lchild->rchild = NULL;

    root->rchild = (laytree)malloc(sizeof(laynode));
    root->rchild->data = 'C';

    root->rchild->lchild = (laytree)malloc(sizeof(laynode));
    root->rchild->lchild->data = 'D';
    root->rchild->lchild->lchild = NULL;
    root->rchild->lchild->rchild = NULL;

    root->rchild->rchild = (laytree)malloc(sizeof(laynode));
    root->rchild->rchild->data = 'E';
    root->rchild->rchild->lchild = NULL;
    root->rchild->rchild->rchild = NULL;

    // 计算双分支节点个数
    int counts = count(root);
    printf("双分支节点个数：%d\n", counts);

    return 0;
}