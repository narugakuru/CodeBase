#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>
#define MaxSize 100
//ջ�Ļ�������

//����ջ
typedef struct
{
    int data[MaxSize];
    int top;
    
} SqStack;

void InitStack(SqStack *s)
{
    s  = (SqStack *) malloc(sizeof(SqStack));
    s->top = -1;
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

void printStack(SqStack *s)
{

    for (int i = s->top; i > 0; i--)
        printf("%d\t", s->data[i]);
        
}

void randonInit(SqStack *s, int n)
{
//    s = (SqStack *)malloc(sizeof(SqStack));
    srand(time(0)); //�����������
    int i = 0;
    int tmp = 0;
    while (i++ < n)
    {
        s->data[++s->top] = rand() % 100 + 1;
    }
    printStack(s);
}

int main()
{
    SqStack *s;
	
    InitStack(s);
	
	printf("��ʼ��"); 
	
    randonInit(s, 10);
	
	printStack(s);
	
    system("pause");
    return 0;
}
