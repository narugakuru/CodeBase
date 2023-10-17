#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAXSIZE 100

typedef struct Sqlist
{
    int data;
    int cur; //�α꣬Ϊ0ʱָ��*
} staticLinkList[MAXSIZE];

//space[0].data���ŵ��ǵ�һ�����ÿ����±�
//��ʼ����̬����
static char * InitList(staticLinkList space[MAXSIZE])
{
    int i;
    srand(time(0)); //�����������
    for (i = 0; i < (MAXSIZE - 1); i++)
    {
        space[i]->cur = i + 1;
        space[i]->data = rand() % 100 + 1; //���������
    }
    space[MAXSIZE - 1]->cur = 0;

    return "OK";
}

//��ȡ���пռ���±�
int MallocNode(staticLinkList space)
{
    int i = space[0].cur;
    //���ص�һ�����ÿ��е��±�
    if (space[0].cur) //���ڵ�һ�������±걻ʹ���ˣ��ѵڶ��������±��space[0]
        space[0].cur = space[i].cur;

    return i;
}


int GetStaticLink(staticLinkList l[MAXSIZE])
{
    int j = 0;
    int i = l[MAXSIZE - 1]->cur;

    while (i)
    {
        printf("%d", l[i]->data);
        i = l[i]->cur;

        j++;
    }
    return j;
}

int main()
{
    char * InitList(staticLinkList space[MAXSIZE]);
    staticLinkList *l;
    InitList(l);

    GetStaticLink(l);

    system("pause");
    return 0;
}
