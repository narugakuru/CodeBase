#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include "Stackdemo.h"

#define MaxSize 100
//栈的基本运算


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

//int main()
//{
//    // SqStack *s = (SqStack *)malloc(sizeof(SqStack));
//    SqStack *s;
//
//    s = InitStack();
//
//    randonInit(s, 5);
//    printf("初始化，赋随机值\n");
//
//    printStack(s);
//
//    system("pause");
//    return 0;
//}
