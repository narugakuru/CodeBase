#include<stdio.h>
#include"StackDemo.h"

// #define MaxSize 50


//�����������ʽ�洢
typedef struct BiTNode
{
    int data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;

/* 
//����ݹ����
void PreOrder(BiTree T){
    if(T!=NULL){
        visit(T);
        PreOrder(T->lchild);
        PreOrder(T->rchild);
    }
}
//����ڵ�
void visit(BiTree T){
    printf("%d",T->data);
}

//�ǵݹ� ������� ,����ע����ѭ��
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

