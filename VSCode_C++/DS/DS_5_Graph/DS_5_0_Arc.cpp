//
// Created by Kim Yang on 2020/9/4.
// Copyright (c) Kim Yang All rights reserved.
//
#include <stdio.h>
#include <stdlib.h>

//邻接表法（顺序+链式存储）
/**定义模块**/

#define MaxVertexNum 100

//"边"/"弧"
typedef struct node
{
    int adjvex;        //边/弧指向哪个结点
    struct node *next; //指向下一条弧的指针
    // InfoType info;  //边权值
} EdgeNode;

//"顶点"
typedef struct vnode
{
    int Vertex;          //顶点信息，数据类型不定，int只是一个例子
    EdgeNode *firstEdge; //第一条边/弧
} VNode;

//用邻接表存储的图
typedef struct
{
    VNode adjlist[MaxVertexNum];
    int n, e; //顶点n，边数e
} LinkedGraph;

/* 测试数据,写在txt文件里，filename即文件路径，建议用绝对路径
 4 4
 0 1 2 3
 0 1
 0 2 
 0 3
 2 3
 1 3
 3 2
 */

//函数定义
bool CreateGraph(LinkedGraph *g, char *filename, int flag);//从文件读取数据建图，flag为1则是无向图，0为有向图
bool PrintGraph(LinkedGraph *g);//打印邻接表

/**定义模块**/

//从文件读取建图
bool CreateGraph(LinkedGraph *g, char *filename, int flag)
{
    int i, j, k;
    FILE *rf;
    EdgeNode *s;

    rf = fopen(filename, "r");

    if (rf)
    {

        fscanf(rf, "%d%d", &g->n, &g->e); //读入顶点数和边数

        for (int i = 0; i < g->n; i++)
        {
            fscanf(rf, "%d", &g->adjlist[i].Vertex);
            g->adjlist[i].firstEdge = NULL;
        } //读入顶点信息

        for (int i = 0; i < g->e; i++)
        {
            fscanf(rf, "%d%d", &i, &j); //由i节点指向j的边
            s = (EdgeNode *)malloc(sizeof(EdgeNode));
            s->adjvex = j;
            //头插入
            s->next = g->adjlist[i].firstEdge;
            g->adjlist[i].firstEdge = s;

            if (flag == 1) // 1代表无向图，ij互相指向对方
            {
                s = (EdgeNode *)malloc(sizeof(EdgeNode));
                s->adjvex = i;
                s->next = g->adjlist[j].firstEdge;
                g->adjlist[j].firstEdge = s;
            }

        } //建立边表
    }
    else
    {
        g->n = 0;
    }

    fclose(rf);

    return false;
}

//全打印
bool PrintGraph(LinkedGraph *g)
{
    if (g == NULL)
        return false;

    printf("顶点数：%d边数：%d\n",g->n,g->e);

    EdgeNode *s;
    for (int i = 0; i < g->n; i++)
    {
        printf("结点:%d指向 ", g->adjlist[i].Vertex);
        s = g->adjlist->firstEdge;
        while (s)
        {
            printf(" %d", s->adjvex);
            s = s->next;
        }
        printf("\n");
    }

    return true;
}

/**实现模块**/
//坐等填坑

/**实现模块**/

/**测试模块**/

void testModule()
{
    printf("开始测试!\n");

    //坐等填坑
    LinkedGraph g;
    
    CreateGraph(&g, "D:\\Code\\VSCode_C++\\DS\\DS_5_Graph\\DS_5_0_Arc.txt", 0);

    PrintGraph(&g);

    printf("结束测试!\n");
}
/**测试模块**/
int main()
{
    testModule();
    system("pause");
    return 0;
}
