#include <stdio.h>
#include <stdlib.h>
#include <iostream>

#define  MaxSize 10

typedef int datatype;

typedef struct LNode
{
    datatype data;
    LNode *next;
} LNode, *LinkList;

typedef struct BiTNode
{
    datatype data;                   // 数据域
    struct BiTNode *lchild, *rchild; // 左右孩子指针
} BiTNode, *BiTree;



#include <stdio.h>
#define MAX_VERtEX_NUM 20                   //顶点的最大个数
#define VRType int                          //表示弧的权值的类型
#define VertexType int                      //图中顶点的数据类型
#define INFINITY 65535
typedef struct {
    VertexType vexs[MAX_VERtEX_NUM];        //存储图中顶点数据
    VRType arcs[MAX_VERtEX_NUM][MAX_VERtEX_NUM];                         //二维数组，记录顶点之间的关系
    int vexnum,arcnum;                      //记录图的顶点数和弧（边）数
}MGraph;

typedef int PathMatrix[MAX_VERtEX_NUM];     //用于存储最短路径中经过的顶点的下标
typedef int ShortPathTable[MAX_VERtEX_NUM]; //用于存储各个最短路径的权值和

//根据顶点本身数据，判断出顶点在二维数组中的位置
int LocateVex(MGraph * G,VertexType v){
    int i=0;
    //遍历一维数组，找到变量v
    for (; i<G->vexnum; i++) {
        if (G->vexs[i]==v) {
            break;
        }
    }
    //如果找不到，输出提示语句，返回-1
    if (i>G->vexnum) {
        printf("no such vertex.\n");
        return -1;
    }
    return i;
}
//构造有向网
void CreateUDG(MGraph *G){
    scanf("%d,%d",&(G->vexnum),&(G->arcnum));
    for (int i=0; i<G->vexnum; i++) {
        scanf("%d",&(G->vexs[i]));
    }
    for (int i=0; i<G->vexnum; i++) {
        for (int j=0; j<G->vexnum; j++) {
            G->arcs[i][j]=INFINITY;
        }
    }
    for (int i=0; i<G->arcnum; i++) {
        int v1,v2,w;
        scanf("%d,%d,%d",&v1,&v2,&w);
        int n=LocateVex(G, v1);
        int m=LocateVex(G, v2);
        if (m==-1 ||n==-1) {
            printf("no this vertex\n");
            return;
        }
        G->arcs[n][m]=w;
    }
}
//迪杰斯特拉算法，v0表示有向网中起始点所在数组中的下标
void ShortestPath_Dijkstra(MGraph G,int v0,PathMatrix *p,ShortPathTable *D){
    int final[MAX_VERtEX_NUM];//用于存储各顶点是否已经确定最短路径的数组
    //对各数组进行初始化
    for (int v=0; v<G.vexnum; v++) {
        final[v]=0;
        (*D)[v]=G.arcs[v0][v];
        (*p)[v]=0;
    }
    //由于以v0位下标的顶点为起始点，所以不用再判断
    (*D)[v0]=0;
    final[v0]=1;
    int k = 0;
    for (int i=0; i<G.vexnum; i++) {
        int min=INFINITY;
        //选择到各顶点权值最小的顶点，即为本次能确定最短路径的顶点
        for (int w=0; w<G.vexnum; w++) {
            if (!final[w]) {
                if ((*D)[w]<min) {
                    k=w;
                    min=(*D)[w];
                }
            }
        }
        //设置该顶点的标志位为1，避免下次重复判断
        final[k]=1;
        //对v0到各顶点的权值进行更新
        for (int w=0; w<G.vexnum; w++) {
            if (!final[w]&&(min+G.arcs[k][w]<(*D)[w])) {
                (*D)[w]=min+G.arcs[k][w];
                (*p)[w]=k;//记录各个最短路径上存在的顶点
            }
        }
    }
}
int main(){
    MGraph G;
    CreateUDG(&G);
    PathMatrix P;
    ShortPathTable D;
    ShortestPath_Dijkstra(G, 0, &P, &D);
    for (int i=1; i<G.vexnum; i++) {
        printf("V%d - V%d的最短路径中的顶点有(逆序)：",0,i);
        printf(" V%d",i);
        int j=i;
        //由于每一段最短路径上都记录着经过的顶点，所以采用嵌套的方式输出即可得到各个最短路径上的所有顶点
        while (P[j]!=0) {
            printf(" V%d",P[j]);
            j=P[j];
        }
        printf(" V0\n");
    }
    printf("源点到各顶点的最短路径长度为:\n");
    for (int i=1; i<G.vexnum; i++) {
        printf("V%d - V%d : %d \n",G.vexs[0],G.vexs[i],D[i]);
    }
    return 0;
}





// 迪杰斯特拉 Dijkstra

#define MAX 10
// 邻接矩阵
typedef struct _graph
{
    char vexs[MAX];       // 顶点集合
    int vexnum;           // 顶点数
    int edgnum;           // 边数
    int matrix[MAX][MAX]; // 邻接矩阵
}Graph, *PGraph;
// 边的结构体
typedef struct _EdgeData
{
    char start; // 边的起点
    char end;   // 边的终点
    int weight; // 边的权重
}EData;
/*
 * Dijkstra最短路径。
 * 即，统计图(G)中"顶点vs"到其它各个顶点的最短路径。
 *
 * 参数说明：
 *        G -- 图
 *       vs -- 起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
 *     prev -- 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
 *     dist -- 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
 */
#define INF 0 //?
void dijkstra(Graph G, int vs, int prev[], int dist[])
{
    int i,j,k;
    int min;
    int tmp;
    int flag[MAX];      // flag[i]=1表示"顶点vs"到"顶点i"的最短路径已成功获取。

    // 初始化
    for (i = 0; i < G.vexnum; i++)
    {
        flag[i] = 0;              // 顶点i的最短路径还没获取到。
        prev[i] = 0;              // 顶点i的前驱顶点为0。
        dist[i] = G.matrix[vs][i];// 顶点i的最短路径为"顶点vs"到"顶点i"的权。
    }

    // 对"顶点vs"自身进行初始化
    flag[vs] = 1;
    dist[vs] = 0;

    // 遍历G.vexnum-1次；每次找出一个顶点的最短路径。
    for (i = 1; i < G.vexnum; i++)
    {
        // 寻找当前最小的路径；
        // 即，在未获取最短路径的顶点中，找到离vs最近的顶点(k)。
        min = INF;
        for (j = 0; j < G.vexnum; j++)
        {
            if (flag[j]==0 && dist[j]<min)
            {
                min = dist[j];
                k = j;
            }
        }
        // 标记"顶点k"为已经获取到最短路径
        flag[k] = 1;

        // 修正当前最短路径和前驱顶点
        // 即，当已经"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点"。
        for (j = 0; j < G.vexnum; j++)
        {
            tmp = (G.matrix[k][j]==INF ? INF : (min + G.matrix[k][j])); // 防止溢出
            if (flag[j] == 0 && (tmp  < dist[j]) )
            {
                dist[j] = tmp;
                prev[j] = k;
            }
        }
    }

    // 打印dijkstra最短路径的结果
    printf("dijkstra(%c): \n", G.vexs[vs]);
    for (i = 0; i < G.vexnum; i++)
        printf("  shortest(%c, %c)=%d\n", G.vexs[vs], G.vexs[i], dist[i]);
}


// 弗洛伊德folyed

/*
 * 克鲁斯卡尔（Kruskal)最小生成树
 */
