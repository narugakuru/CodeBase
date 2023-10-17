#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#include "Stackdemo.h"

#define MaxSize 100
//ջ�Ļ�������


//����һ����ʼ����ջ

SqStack *InitStack()
{
    SqStack *s = (SqStack *)malloc(sizeof(SqStack));
    s->top = -1;
    return s;
}
//�пգ�tureΪ��
bool StackEmpty(SqStack *s)
{
    if (s->top == -1)
        return true;
    else
        return false;
}
//��ջ
bool Push(SqStack *s, int x)
{
    if (s->top == MaxSize - 1)
    {
        return false;
    }
    s->top++;
    s->data[s->top] = x;
    printf("\n�ߺ���%lf\n", s->data[s->top]);
    return true;
}
//��ջ
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
//��ȡջ��Ԫ��
bool GetTop(SqStack *s, int *x)
{
    if (s->top == -1)
    {
        return false;
    }

    *x = s->data[s->top];
    return true;
}
//�鿴ջ����Ԫ�أ�����ջ
void printStack(SqStack *s)
{
    printf("��ӡ\n");
    for (int i = s->top; i >= 0; i--)
        printf("%d\t", s->data[i]);

}
//���������ջ
void randonInit(SqStack *s, int n)
{
    // s = (SqStack *)malloc(sizeof(SqStack));
    srand(time(0)); //�����������
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
//    printf("��ʼ���������ֵ\n");
//
//    printStack(s);
//
//    system("pause");
//    return 0;
//}
