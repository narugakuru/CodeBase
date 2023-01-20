#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#define MaxSize 100

typedef struct
{
    int data[MaxSize];
    int top;

} SqStack;


//返回一个初始化的栈
SqStack *InitStack();
//判空，ture为空
bool StackEmpty(SqStack *s);
//入栈
bool Push(SqStack *s, int x);
//出栈
bool Pop(SqStack *s, int *x);
//获取栈顶元素
bool GetTop(SqStack *s, int *x);
//查看栈所有元素，不出栈
void printStack(SqStack *s);
//随机序列入栈
void randonInit(SqStack *s, int n);
