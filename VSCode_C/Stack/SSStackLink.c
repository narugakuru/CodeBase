#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

#define MaxSize 100

typedef struct StackLink
{
    double data[MaxSize];
    int top;

} SqStack, s1;

bool Push(SqStack *s, double e)
{

    if (s->top == MaxSize - 1)
    {
        return false;
    }
    s->top++;
    s->data[s->top] = e;
    printf("\n芜湖湖%lf\n", s->data[s->top]);
    return true;
}

double pop(SqStack *s, double e)
{
    if (!s->top)
    {
        return false;
    }

    printf("error:%lf", s->data[s->top]);

    e = s->data[s->top];
    s->data[s->top] = 0;
    s->top--;
    return e;
}

SqStack *init(int n)
{

    SqStack *s = (SqStack *)malloc(sizeof(SqStack));
    if (n>MaxSize-1)
    {
        return false;
    }
    
    int i = 0;
    srand(time(0));
    for (i = 0; i <= n; i++)
    {
        s->data[i] = rand() % 100 + 1;
    }
    s->top = n;
    return s;
}

void printStack(SqStack *s)
{

    for (int i = s->top; i > 0; i--)
        printf("%0.lf\t", s->data[i]);
}

void f(SqStack *s)
{
    printf("1.初始化\n2.入栈\n3.出栈\n4.打印所有栈内元素\n");
    int i;
    double temp;
    while (i != 886)
    {
        printf("请输入操作:\n");
        scanf("%d", &i);
        switch (i)
        {
        case 1:
            s = init(10);
            printf("栈初始化完成\n");
            break;
        case 2:
            printf("输入入栈元素");
            scanf("%lf", &temp);
            Push(s, temp);
            printf("已入栈\n");
            break;
        case 3:
            printf("元素出栈%2.lf\n", pop(s, temp));
            break;
        case 4:
            printf("打印所有元素\n");
            printStack(s);
            break;
        default:
            printf("bye~~~\n");
            break;
        }
    }
}

void main()
{

    SqStack *s;
    double *e;

    f(s);

    system("pause");
}