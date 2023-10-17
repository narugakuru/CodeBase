#include <iostream>
#include <queue>
#define MAX 10
#define INF 99
using namespace std;

class MGraph
{
public:
	void Init()
	{
		for (int i = 0; i < MAX; i++)
		{
			Visited[i] = 0;
			for (int j = 0; j < MAX; j++)
				arc[i][j] = INF;
		}
	}
	//isited数组初始化
	void InitVisited()
	{
		for (int i = 0; i < MAX; i++)
			Visited[i] = 0;
	}
	//创建邻接矩阵
	void Creat()
	{
		int i, j, k, w;
		cout << "请输入顶点数和边数" << endl;
		cin >> vexnum >> edgenum;
		cout << "请输入顶点信息" << endl;
		for (i = 0; i < vexnum; i++)
		{
			Vertex[i] = i; //存储序号
			cin >> Vername[i];
		}
		for (k = 0; k < edgenum; k++)
		{
			cout << "请输入边的下标i，j和权w";
			cin >> i >> j >> w;
			arc[j][i] = arc[i][j] = w;
		}
	}
	//输出邻接矩阵
	void Print()
	{
		for (int i = 0; i < vexnum; i++)
		{
			for (int j = 0; j < vexnum; j++)
				printf("%4d", arc[i][j]);
			cout << endl;
		}
	}
	//返回顶点所在的位置
	int LocateVex(char ch)
	{
		int i;
		for (i = 0; i < vexnum; i++)
		{
			if (ch == Vername[i])
				break;
			if (i == vexnum)
				return -1;
			else
				return i;
		}
	}
	//增加某个结点
	void InsertVex(char ch)
	{
		Vertex[vexnum] = vexnum;
		Vername[vexnum] = ch;
		vexnum++; //顶点数自增
	}
	//删除某个结点
	void DleteVex(char ch)
	{
		int i, j, k;
		//遍历寻找顶点位置
		for (i = 0; i < vexnum; i++)
			if (ch == Vername[i])
				break;
		//Vername数组循环左移
		for (j = i; j < vexnum - 1; j++)
			Vername[j] = Vername[j + 1]; //覆盖
		//arc数组循环左移和上移
		//上移
		for (j = i; j < vexnum - 1; j++)
			for (k = 0; k < vexnum; k++)
				arc[j][k] = arc[j + 1][k];
		//左移
		for (j = 0; j < vexnum - 1; j++)
			for (k = i; k < vexnum - 1; k++)
				arc[j][k] = arc[j][k + 1];
		vexnum--;
	}
	//增加某条边
	void InsertEdge(int i, int j, int w)
	{
		arc[i][j] = w;
		edgenum++;
	}
	//删除某条边
	void DleteEdge(int i, int j)
	{
		arc[i][j] = INF;
		edgenum--;
	}
	//返回第一个邻接顶点
	char FirstAdjVex(char ch)
	{
		int i, j;
		for (i = 0; i < vexnum; i++)
			if (ch == Vername[i])
				break;
		for (j = 0; j < vexnum; j++)
			if (arc[i][j] != INF)
				break;
		if (j == vexnum)
			return '\0';
		else
			return Vername[j];
	}
	//深度优先遍历
	void DFS(char ch)
	{
		int i, j, k;
		int lv = LocateVex(ch);
		cout << Vername[lv] << "lv" << lv << endl; //输出当前顶点
		Visited[lv] = 1;						   //表示访问过了
		for (i = 0; i < 3; i++)
			if (Visited[i] == 0 && arc[lv][i] != INF)
				DFS(Vername[i]);
	}
	//广度优先遍历
	void BFS(char ch)
	{
		queue<int> q;			  //定义队列
		int l = LocateVex(ch);	  //获取顶点位置
		cout << Vername[l] << ""; //输出当前顶点信息
		InitVisited();			  //isited队列初始化
		q.push(1);				  //入队
		Visited[l] = l;
		//一直循环至队列空
		while (q.size())
		{
			l = q.front(); //获取队头信息
			q.pop();	   //出队
			for (int i = 0; i < vexnum; i++)
				if (Visited[i] = 0 && arc[l][i] != INF)
				{
					cout << Vername[i] << ""; //访问顶点
					q.push(i);				  //入队
					Visited[i] = 1;
				}
		}
	}

private:
	int Vertex[MAX];   //存储结点的序号
	char Vername[MAX]; //存储节点的名字
	int arc[MAX][MAX]; //邻接矩阵
	int vexnum;		   //顶点数
	int edgenum;	   //边数
	int Visited[MAX];  //是否访问
};
int main()
{
	MGraph MyGraph;
	char ch;
	MyGraph.Init();
	MyGraph.Creat();
	MyGraph.Print();
	//增加和删除

	cout << "请输入要增加的顶点信息";
	cin >> ch;
	MyGraph.InsertVex(ch);
	MyGraph.Print();
	cout << "请输入要删除的顶点信息";
	cin >> ch;
	MyGraph.DleteVex(ch);
	MyGraph.Print();
	//返回第一个邻接顶点
	cout << "请输入你要查找的顶点";
	cin >> ch;
	ch = MyGraph.FirstAdjVex(ch);
	if (ch == '\0')
		cout << "该顶点不存在邻接顶点" << endl;
	else
		cout << "该顶点的第一个邻接顶点是" << ch;
	//深度优先遍历
	cout << "请输入起点的信息";
	cin >> ch;
	cout << "深度优先遍历";
	MyGraph.DFS(ch);
	cout << endl;
	//广度优先遍历
	cout << "请输入起点顶点的信息";
	cin >> ch;
	cout << "广度优先遍历" << endl;
	MyGraph.BFS(ch);
	cout << endl;
	return 0;
}
