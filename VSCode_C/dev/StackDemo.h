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


//����һ����ʼ����ջ
SqStack *InitStack();
//�пգ�tureΪ��
bool StackEmpty(SqStack *s);
//��ջ
bool Push(SqStack *s, int x);
//��ջ
bool Pop(SqStack *s, int *x);
//��ȡջ��Ԫ��
bool GetTop(SqStack *s, int *x);
//�鿴ջ����Ԫ�أ�����ջ
void printStack(SqStack *s);
//���������ջ
void randonInit(SqStack *s, int n);

