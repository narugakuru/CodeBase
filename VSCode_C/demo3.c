// 图由顶点和边组成
// 树是图的子集

// DFS深度遍历：一条路走到黑，不撞南墙不回头，撞墙之后再回头，回头之后再撞墙
// 1. 找一个结点访问
// 2. 找这个结点可以到达的结点去访问
// 3. 重复第一步直到所有结点访问完毕

// BFS广度遍历：
// 创建一个队列，并将起始顶点入队。
// 创建一个访问标记数组，并将起始顶点标记为已访问。
// 从队列中取出一个顶点，输出该顶点的值。
// 遍历该顶点的所有相邻顶点，如果相邻顶点未被访问过，则将其入队并标记为已访问。
// 重复步骤3和步骤4，直到队列为空。
// 当队列为空时，表示所有顶点都已被访问完毕。

// 定义了一个图的结构体 Graph，包含顶点数组 vexs、邻接矩阵 arcs、顶点数 vexNum 和边数 arcNum。
// initGraph 函数用于初始化图，动态分配内存，并设置顶点数和边数。
// createGraph 函数用于创建图，将顶点和邻接矩阵的数据存入图的结构体中。
// DFS 函数是深度优先搜索的实现，它接受一个图、一个访问标记数组 visited 和一个起始顶点的索引 index。
// 函数首先输出当前顶点的值，然后递归地访问与当前顶点相邻且未被访问过的顶点。
// main 函数是程序的入口，它创建一个包含5个顶点的图，并调用 DFS 函数进行深度优先搜索。

#include <stdio.h>
#include <stdlib.h>

typedef struct Graph
{
    char *vexs; // 顶点数组
    int **arcs; // 邻接矩阵
    int vexNum; // 顶点数
    int arcNum; // 边数
} Graph;

// 初始化图
Graph *initGraph(int vexNum)
{
    Graph *G = (Graph *)malloc(sizeof(Graph));
    G->vexs = (char *)malloc(sizeof(char) * vexNum);
    G->arcs = (int **)malloc(sizeof(int *) * vexNum);
    for (int i = 0; i < vexNum; i++)
    {
        G->arcs[i] = (int *)malloc(sizeof(int) * vexNum);
    }
    G->vexNum = vexNum;
    G->arcNum = 0;
    return G;
}

// 创建图
void createGraph(Graph *G, char *vexs, int **arcs)
{
    for (int i = 0; i < G->vexNum; i++)
    {
        G->vexs[i] = vexs[i];
        for (int j = 0; j < G->vexNum; j++)
        {
            // 就是把arcs复制给图地arcs
            G->arcs[i][j] = *(arcs + i * G->vexNum + j);
            // G->arcs[i][j] = arcs[i][j];
            // arcs 是一个指向邻接矩阵的二维数组的指针。
            // arcs + i * G->vexNum 表示将指针arcs向后移动 i * G->vexNum 个元素的位置。
            // 这里的 i 表示当前行的索引，G->vexNum 表示图中顶点的数量。这样可以定位到邻接矩阵中第 i 行的起始位置。
            // *(arcs + i * G->vexNum + j) 表示取出指针arcs + i * G->vexNum + j 所指向的值。
            // 这里的 j 表示当前列的索引。通过这个表达式，我们可以获取到邻接矩阵中第 i 行第 j 列的值。
            if (G->arcs[i][j] != 0)
                G->arcNum++;
        }
    }
    // 无向图
    G->arcNum /= 2;
}

// 深度优先搜索
void DFS(Graph *G, int *visited, int index)
{
    printf("%c\t", G->vexs[index]); // 输出当前顶点的值

    visited[index] = 1; // 标记当前顶点为已访问

    for (int i = 0; i < G->vexNum; i++)
    {
        if (G->arcs[index][i] == 1 && !visited[i]) // 如果与当前顶点相邻且未被访问过
        {
            // printf("%c", G->vexs[i]);
            DFS(G, visited, i); // 递归访问相邻顶点
        }
    }
}

int main()
{
    Graph *G = initGraph(5);                               // 创建一个包含5个顶点的图
    int *visited = (int *)malloc(sizeof(int) * G->vexNum); // 创建访问标记数组
    for (int i = 0; i < G->vexNum; i++)
        visited[i] = 0; // 初始化访问标记数组
    int arcs[5][5] = {
        0, 1, 1, 1, 0,
        1, 0, 1, 1, 1,
        1, 1, 0, 0, 0,
        1, 1, 0, 0, 1,
        0, 1, 0, 1, 0};
    createGraph(G, "ABCDE", (int **)arcs); // 将顶点和邻接矩阵的数据存入图的结构体中
    printf("深度优先搜索:\n");
    for (int i = 0; i < G->vexNum; i++)
    {
        for (int j = 0; i < G->vexNum; i++)
            printf("%d ", G->arcs[i][j]);
        printf("\n");
    }
    DFS(G, visited, 0); // 深度优先搜索
    printf("\n");
    return 0;
}
