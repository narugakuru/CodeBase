#include<stdio.h>
#include"StackDemo.h"

// #define MaxSize 50


//定义二叉树链式存储
typedef struct BiTNode
{
    int data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;

/* 
//先序递归遍历
void PreOrder(BiTree T){
    if(T!=NULL){
        visit(T);
        PreOrder(T->lchild);
        PreOrder(T->rchild);
    }
}
//处理节点
void visit(BiTree T){
    printf("%d",T->data);
}

//非递归 中序遍历 ,先序注意死循环
void InOrder2(BiTree T){
    InitStack(S);
    BiTree p=T;
    while (p||IsEmpty(S))
    {   
        
    }
    
}
 */
int main(){
    SqStack *s;
    
    s = InitStack();

    randonInit(s,5);

    printStack(s);

    system("pause");
    return 0;
}

