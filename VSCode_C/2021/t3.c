#include <stdio.h>
/*
 首先遍历二叉树root，
 当T为空树时，返回0，
 当T只有根root时，返回1，
 使用LH,RH记录左右子树的深度，
 当LH>RH时，返回LH加1，反之，返回RH加1
 */

typedef struct node
{
    int info;
    struct node *lchild, *rchild;

} bintnode;

int Depth(bintnode *root)
{
    int LH, RH;
    if (!root)
    {
        return 0;
    }
    else if (root && root->lchild == NULL && root->rchild == NULL)
    {
        return 1;
    }
    else
    {
        LH = Depth(root->lchild);
        RH = Depth(root->rchild);
        if (LH > RH)
            return LH + 1;
        else
            return RH + 1;
    }
}