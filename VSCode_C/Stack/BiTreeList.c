#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#define MaxSize 100

//栈
typedef struct
{
    int data[MaxSize];
    int top;

} SqStack;
//返回一个初始化的栈
SqStack *InitStack()
{
    SqStack *s = (SqStack *)malloc(sizeof(SqStack));
    s->top = -1;
    return s;
}
//判空，ture为空
bool StackEmpty(SqStack *s)
{
    if (s->top == -1)
        return true;
    else
        return false;
}
//入栈
bool Push(SqStack *s, int x)
{
    if (s->top == MaxSize - 1)
    {
        return false;
    }
    s->top++;
    s->data[s->top] = x;
    printf("\n芜湖湖%lf\n", s->data[s->top]);
    return true;
}
//出栈
bool Pop(SqStack *s, int *x)
{
    if (s->top == -1)
    {
        return false;
    }
    *x = s->data[s->top--];
    printf("%d", *x);
    return true;
}
//获取栈顶元素
bool GetTop(SqStack *s, int *x)
{
    if (s->top == -1)
    {
        return false;
    }

    *x = s->data[s->top];
    return true;
}
//查看栈所有元素，不出栈
void printStack(SqStack *s)
{
    printf("打印\n");
    for (int i = s->top; i >= 0; i--)
        printf("%d\t", s->data[i]);

}
//随机序列入栈
void randonInit(SqStack *s, int n)
{
    // s = (SqStack *)malloc(sizeof(SqStack));
    srand(time(0)); //生成随机数种
    int i = 0;
    int tmp = 0;
    while (i++ < n)
    {
        s->top++;
        tmp = rand() % 100 + 1;
        s->data[s->top] = tmp;
    }
    printStack(s);
}



//定义二叉树链式存储
typedef struct BiTNode
{
    int data;
    struct BiTNode *lchild,*rchild;
}BiTNode,*BiTree;

//处理节点
void visit(BiTree T){
    printf("%d",T->data);
}

//先序递归遍历
void PreOrder(BiTree T){
    if(T!=NULL){
        visit(T);
        PreOrder(T->lchild);
        PreOrder(T->rchild);
    }
}


/* //非递归 中序遍历 ,先序注意死循环
void InOrder2(BiTree T){
    SqStack *s;
    s = InitStack();
    BiTree p=T;
    
} */



int main(){


    system("pause");
    return 0;
}
