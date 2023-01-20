#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MaxSize 100

typedef struct StackLink
{
    double data[MaxSize];
    int top;

} SqStack;

SqStack *init()
{              
    SqStack *s = (SqStack *)malloc(sizeof(SqStack));

    int i = 0;
    srand(time(0));
    for (i = 0; i < MaxSize - 1; i++)
    {
        s->data[i] = rand() % 100 + 1;
    }
    s->top = MaxSize - 1;
    return s;
}

// ȫ����ӡ
void printStack(SqStack *s)
{
    // ��ջ����ʼ��ӡ
    for (int i = s->top; i > 0; i--)
        printf("%0.lf\t", s->data[i]);
}

void main()

{

    SqStack *s;

    s = init();
    printStack(s);

    printf("yes");

    system("pause");
}