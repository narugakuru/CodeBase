#include <stdio.h>
int main()
{
	int claim[5][3] = { { 7,5,3 },{ 3,2,2 },{ 9,0,2 },{ 2,2,2 },{ 4,3,3 } };//各线程最大需求量
	int allocation[5][3] = { { 0,1,0 },{ 2,0,0 },{ 3,0,2 },{ 2,1,1 },{ 0,0,2 } };//各线程已分配资源
	int i, j, k, l = 0, count = 0, m = 0;
	int C_A[5][3] = { { 0,0,0 },{ 0,0,0 },{ 0,0,0 },{ 0,0,0 },{ 0,0,0 } };//各进程仍需要的各类资源
	int result[5] = { -1,-1,-1,-1,-1 };//存放预分配成功的线程
	int currentavail[3] = { 3,3,2 };//当前可分配资源
	printf("银行家总共拥有的各类资源的总数：\n     A  B  C\n     10 5  7\n");
	printf("银行目前仍剩下的各类资源的数量：\n     A  B  C\n     3  3  2\n");
	printf("各进程对各类资源的最大需求量：\n     A  B  C\n");
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
	printf("各进程已分配到的各类资源：\n     A  B  C\n");
	for (i = 0; i<5; i++)
	{
		printf("P%d: ", i);
		for (j = 0; j<3; j++)
			printf(" %d ", allocation[i][j]);
		printf("\n");
	}
	printf("各进程仍需的各类资源数量：\n     A  B  C\n");
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
					//判断各线程对各资源仍需量是否小于当前可分配资源总量,此量为正数才正常
					if (C_A[k][j] <= currentavail[j] && C_A[k][j] >= 0)
					{
						//把满足条件的进程的已分配资源加到当前可分配资源中
						currentavail[j] = currentavail[j] + allocation[k][j];
						m++;
						if (m == 3)
						{
							result[l] = k;//只有ABC三类资源都满足才把相应的线程记入数组result中
							m = 0;
						}
					}
					else break;//否则退出循环,打印"系统不安全"
					l++;
			}
		for (i = 0; i<l; i++)
			if (result[i] != -1)
			{
				printf("P%d->", result[i]);//把预分配成功的先打印出来
				count++;
			}
		l = 0;//清零,为下一轮的预分配做准备
	}
	if (count == 5)
		printf("\n系统安全!上行所示为其中一个进程安全序列\n");
	else
		printf("\n系统不安全!\n");
}
