#include<stdio.h> 
#include<conio.h> 
#define M 4 
#define N 17 
#define Myprintf printf("|---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---|\n")	/*������*/ 
typedef struct page
{
	int num;	/*��¼ҳ���*/
	int time;	/*��¼�����ڴ�ʱ��*/
}Page;			/* ҳ���߼��ṹ���ṹΪ�����㷨ʵ�����*/

Page b[M];		/*�ڴ浥Ԫ��*/
int c[M][N];	/*�ݱ����ڴ浱ǰ��״̬��������*/
int queue[100];	/*��¼�������*/
int K;		/*������м�������*/

//��ʼ���ڴ浥Ԫ�������� 
void Init(Page *b, int c[M][N])
{
	int i, j;
	for (i = 0; i<N; i++)
	{
		b[i].num = -1;
		b[i].time = N - i - 1;
	}
	for (i = 0; i<M; i++)
		for (j = 0; j<N; j++)
			c[i][j] = -1;
}

//ȡ�����ڴ���ͣ����õ�ҳ��,Ĭ��״̬��Ϊ��������ҳ��
int GetMax(Page *b)
{
	int i;
	int max = -1;
	int tag = 0;
	for (i = 0; i<M; i++)
	{
		if (b[i].time>max)
		{
			max = b[i].time;
			tag = i;
		}
	}
	return tag;
}

//�ж�ҳ���Ƿ������ڴ���
int	Equation(int fold, Page *b)
{
	int i;
	for (i = 0; i<M; i++)
		if (fold == b[i].num)  return i;
	return -1;
}

//LRU�㷨
void Lru(int fold, Page *b)
{
	int i;
	int val;
	val = Equation(fold, b);
	if (val >= 0)
	{
		b[val].time = 0;
		for (i = 0; i<M; i++)
			if (i != val)  b[i].time++;
	}
	else
	{
		queue[++K] = fold;/*��¼����ҳ��*/
		val = GetMax(b);
		b[val].num = fold;
		b[val].time = 0;
		for (i = 0; i<M; i++)
			if (i != val) b[i].time++;
	}
}

void main()
{
	int a[N] = { 1,0,1,0,2,4,1,0,0,8,7,5,4,3,2,3,4 };
	int i, j;
start:
	K = -1;
	Init(b, c);
	for (i = 0; i<N; i++)
	{
		Lru(a[i], b);
		c[0][i] = a[i];
		/*��¼��ǰ���ڴ浥Ԫ�е�ҳ��*/
		for (j = 0; j<M; j++)
			c[j][i] = b[j].num;
	}
	/*������*/
	printf("�ڴ�״̬Ϊ��\n");
	Myprintf;
	for (j = 0; j<N; j++)
		printf("|%2d ", a[j]);
	printf("|\n");
	Myprintf;
	for (i = 0; i<M; i++)
	{
		for (j = 0; j<N; j++)
			if (c[i][j] == -1) printf("|%2c ", 32);
			else
				printf("|%2d ", c[i][j]);
		printf("|\n");
	}
	Myprintf;
	printf("\n�������Ϊ:");
	for (i = 0; i<K + 1; i++)
		printf("%3d", queue[i]);
	printf("\nȱҳ����Ϊ��%6d\nȱҳ�ʣ�%16.6f", K + 1, (float)(K + 1) / N);
	printf("\nAre you continuing!\ty?");
	if (_getch() == 'y')
		goto start;
}
