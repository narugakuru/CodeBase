#include <stdio.h>
int main()
{
	int claim[5][3] = { { 7,5,3 },{ 3,2,2 },{ 9,0,2 },{ 2,2,2 },{ 4,3,3 } };//���߳����������
	int allocation[5][3] = { { 0,1,0 },{ 2,0,0 },{ 3,0,2 },{ 2,1,1 },{ 0,0,2 } };//���߳��ѷ�����Դ
	int i, j, k, l = 0, count = 0, m = 0;
	int C_A[5][3] = { { 0,0,0 },{ 0,0,0 },{ 0,0,0 },{ 0,0,0 },{ 0,0,0 } };//����������Ҫ�ĸ�����Դ
	int result[5] = { -1,-1,-1,-1,-1 };//���Ԥ����ɹ����߳�
	int currentavail[3] = { 3,3,2 };//��ǰ�ɷ�����Դ
	printf("���м��ܹ�ӵ�еĸ�����Դ��������\n     A  B  C\n     10 5  7\n");
	printf("����Ŀǰ��ʣ�µĸ�����Դ��������\n     A  B  C\n     3  3  2\n");
	printf("�����̶Ը�����Դ�������������\n     A  B  C\n");
	for (i = 0; i<5; i++)
	{
		printf("P%d: ", i);
		for (j = 0; j<3; j++)
		{
			printf(" %d ", claim[i][j]);
			C_A[i][j] = claim[i][j] - allocation[i][j];
		}
		printf("\n");
	}
	printf("�������ѷ��䵽�ĸ�����Դ��\n     A  B  C\n");
	for (i = 0; i<5; i++)
	{
		printf("P%d: ", i);
		for (j = 0; j<3; j++)
			printf(" %d ", allocation[i][j]);
		printf("\n");
	}
	printf("����������ĸ�����Դ������\n     A  B  C\n");
	for (i = 0; i<5; i++)
	{
		printf("P%d: ", i);
		for (j = 0; j<3; j++)
			printf(" %d ", C_A[i][j]);
		printf("\n");
	}
	while (result[l] == -1)//
	{
		for (k = 0; k<5; k++)
			if (result[k] == -1)
			{
				for (j = 0; j<3; j++)
					//�жϸ��̶߳Ը���Դ�������Ƿ�С�ڵ�ǰ�ɷ�����Դ����,����Ϊ����������
					if (C_A[k][j] <= currentavail[j] && C_A[k][j] >= 0)
					{
						//�����������Ľ��̵��ѷ�����Դ�ӵ���ǰ�ɷ�����Դ��
						currentavail[j] = currentavail[j] + allocation[k][j];
						m++;
						if (m == 3)
						{
							result[l] = k;//ֻ��ABC������Դ������Ű���Ӧ���̼߳�������result��
							m = 0;
						}
					}
					else break;//�����˳�ѭ��,��ӡ"ϵͳ����ȫ"
					l++;
			}
		for (i = 0; i<l; i++)
			if (result[i] != -1)
			{
				printf("P%d->", result[i]);//��Ԥ����ɹ����ȴ�ӡ����
				count++;
			}
		l = 0;//����,Ϊ��һ�ֵ�Ԥ������׼��
	}
	if (count == 5)
		printf("\nϵͳ��ȫ!������ʾΪ����һ�����̰�ȫ����\n");
	else
		printf("\nϵͳ����ȫ!\n");
}
