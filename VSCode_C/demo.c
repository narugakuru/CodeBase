#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode
{
    char data;
    struct TreeNode *lchild;
    struct TreeNode *rchild;
} TreeNode;

void createTree(TreeNode **T, char *data, int *index)
{
    char ch;
    ch = data[*index];
    *index += 1;
    if (ch == '#')
    {
        // 此时为空结点
        *T = NULL;
    }
    else
    {
        *T = (TreeNode *)malloc(sizeof(TreeNode));
        (*T)->data = ch;
        // 创建左右子树，逻辑一致，进行递归
        createTree(&((*T)->lchild), data, index);
        createTree(&((*T)->rchild), data, index);
    }
}

void preOrder(TreeNode *T)
{
    if (T == NULL)
    {
        return;
    }
    else
    {
        printf("%c", T->data);
        // 处理左右孩子
        preOrder(T->lchild);
        preOrder(T->lchild);
    }
}

void inOrder(TreeNode *T)
{
    if (T == NULL)
    {
        return;
    }
    else
    {
        inOrder(T->lchild);
        printf("%c", T->data);
        inOrder(T->rchild);
    }
}

void postOrder(TreeNode *T)
{
    if (T == NULL)
    {
        return;
    }
    else
    {
        inOrder(T->lchild);
        inOrder(T->rchild);
        printf("%c", T->data);
    }
}

int main(int argc, char *argv[])
{
    TreeNode *T;
    int index = 0;
    createTree(&T, argv[1], &index);
    preOrder(T);
    printf("\n");
    inOrder(T);
    printf("\n");
    postOrder(T);
    return 0;
}
