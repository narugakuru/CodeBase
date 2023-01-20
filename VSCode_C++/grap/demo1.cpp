#include <stdio.h>

#define MAXVEX 20           //最大顶点数 
#define FINITY 100          //代表    ∞ 
typedef char VertexType;    //顶点类型

//邻接矩阵存储结构
typedef struct{
    VertexType vexs[MAXVEX];    //顶点数组 
    int arc[MAXVEX][MAXVEX];    //边的权值 
    int numVertexes,numEdges;   //实际顶点数和边数 
}MGraph;

//创建邻接矩阵
void Create(MGraph *G){

    int i,j;

    printf("输入顶点数和边数:\n");
    scanf("%d%d",&G->numVertexes,&G->numEdges);

    //输入顶点信息,创建顶点表 
    printf("输入顶点信息:\n");
    for(i=0;i<G->numVertexes;i++){
        getchar();
        scanf("%c",&G->vexs[i]);
    }

    //输入邻接矩阵
    printf("输入邻接矩阵:\n");
    for(i=0;i<G->numVertexes;i++){
        for(j=0;j<G->numVertexes;j++){
            scanf("%d",&G->arc[i][j]);
            if(G->arc[i][j]==0)
                G->arc[i][j]=FINITY;
        }
    } 

    //输出邻接矩阵
    printf("邻接矩阵为:\n");
    for(i=0;i<G->numVertexes;i++){
        for(j=0;j<G->numVertexes;j++)
            printf("%3d ",G->arc[i][j]);
        printf("\n");
    } 
} 

int main(){

    MGraph  G;

    Create(&G);
}