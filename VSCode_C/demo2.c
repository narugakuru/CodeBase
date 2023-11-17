#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode
{
    char data;
    struct TreeNode *lchild;
    struct TreeNode *rchild;
} TreeNode;

void createTree(TreeNode **T, char *data, int *index, int length)
{
    char ch;
    ch = data[*index];
    *index += 1;
    if (ch == '\0' || *index > length)
    {
        // 此时为空结点
        *T = NULL;
    }
    else
    {
        *T = (TreeNode *)malloc(sizeof(TreeNode));
        (*T)->data = ch;
        // 创建左右子树，逻辑一致，进行递归
        createTree(&((*T)->lchild), data, index, length);
        createTree(&((*T)->rchild), data, index, length);
    }
}

void inOrder(TreeNode *T)
{
    if (T != NULL)
    {
        inOrder(T->lchild);
        printf("%c ", T->data);
        inOrder(T->rchild);
    }
}

void preOrder(TreeNode *T)
{
    if (T != NULL)
    {
        printf("%c ", T->data);
        preOrder(T->lchild);
        preOrder(T->rchild);
    }
}

void postOrder(TreeNode *T)
{
    if (T != NULL)
    {
        postOrder(T->lchild);
        postOrder(T->rchild);
        printf("%c ", T->data);
    }
}

int main()
{
    char data[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int length = sizeof(data) / sizeof(data[0]) - 1;
    int index = 0;
    TreeNode *root = NULL;

    createTree(&root, data, &index, length);

    printf("先序: ");
    preOrder(root);
    printf("\n中序: ");
    inOrder(root);
    printf("\n后序: ");
    postOrder(root);
    printf("\n");
    system("pause");

    return 0;
}
