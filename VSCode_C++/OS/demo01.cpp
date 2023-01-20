// 最基本的PCB进程管理
# include <stdio.h>
# include <stdlib.h>
# include <string.h>

//!表示逻辑反，例如a：10，则a的逻辑反为0；a：0.逻辑反为1

struct jincheng_type {    //进程类型结构体建立
	int pid;
	int youxian;
	int daxiao;
	int zhuangtai; //标志进程状态，0 为不在内存，1 为在内存，2 为挂起
	char info[10];
};
struct jincheng_type neicun[20];    //内存用21长度的数组表示
int shumu = 0, guaqi = 0, pid, flag,bj,i,j = 0;   //初始化

     // num=0,guaqi=0,bj,i,j,pid;/*定义相关参数变量*/

//创建进程
void create() {
	if (shumu >= 20) printf("\n 内存已满，请先换出或杀死进程\n");
	else {
		int i;
		for (i = 0; i<20; i++)
			//定位，找到可以还未创建的进程
			if (neicun[i].zhuangtai == 0) break;

		printf("\n 请输入新进程 pid\n");
		scanf("%d", &(neicun[i].pid), 5);
		for (int j = 0; j < i; j++) {
			if (neicun[i].pid == neicun[j].pid) {
				printf("\n 该进程已存在\n");
				return;
			}
		}
		printf("\n 请输入新进程优先级\n");
		scanf("%d", &(neicun[i].youxian), 2);
		printf("\n 请输入新进程大小\n");
		scanf("%d", &(neicun[i].daxiao), 5);
		printf("\n 请输入新进程内容\n");
		scanf("%s", &(neicun[i].info), 20);
		//创建进程，使标记位为 1
		neicun[i].zhuangtai = 1;
		shumu++;
	}
}

void run() {     //查看当前运行进程
	for (int i = 0; i<20; i++) {
		if (neicun[i].zhuangtai == 1) {     //如若进程状态设为1，表示其再内存中，并
			//输出运行进程的各个属性值
			printf("\n pid= %d", neicun[i].pid);
			printf(" youxian= %d", neicun[i].youxian);
			printf(" daxiao= %d", neicun[i].daxiao);
			printf(" zhuangtai= %d", neicun[i].zhuangtai);
			printf(" info= %s", neicun[i].info);
			flag = 1;
		}
	}
	if (!flag) printf("\n 当前没有运行进程\n");//标记状态若为0，表示该进程未创建于内存中
}

//换出进程
void huanchu() {
	if (!shumu) {        //若内存中无进程
		printf("当前没有运行进程\n");
		return;
	}
	printf("\n 输入换出进程的 ID 值");
	scanf("%d", &pid, 5);
	for (int i = 0; i<20; i++) {
		//定位，找到所要换出的进程，根据其状态做相应处理
		if (pid == neicun[i].pid) {
			if (neicun[i].zhuangtai == 1) {
				neicun[i].zhuangtai = 2;  //将进程换出，状态设置为挂起，
				guaqi++;
				printf("\n 已经成功换出进程\n");
			}
			else if (neicun[i].zhuangtai == 0) printf("\n 要换出的进程不存在\n");
			else printf("\n 要换出的进程已被挂起\n");
			flag = 1;
			break;
		}
	}
	//找不到，则说明进程不存在
	if (flag == 0) printf("\n 要换出的进程不存在\n");
}

//杀死进程
void kill() {
	 if(!shumu)   //首先判断是否存在进程
  { printf("\n当前没有运行的进程\n" );
    return;
  }
 printf("\n请输入要杀死的进程的pid值\n" );
    scanf("%d",&pid);
    bj=0;  //初始化临时变量
    for(i=0;i<20;i++)    //与进程数组中循环
      {
        if(pid==neicun[i].pid)    //判断是否存在对应进程
         { if(neicun[i].zhuangtai==1)     //进程状态判断
           {neicun[i].zhuangtai=0; //将该进程的状态设为不在内存中
            shumu--;  //内存中的进程数目减少一个
           printf("\n该进程已成功杀死\n" );
           }
           else if(neicun[i].zhuangtai==0)  //若进程状态为0
           printf("\n要杀死的进程不存在\n" );
           else printf("\n要杀死的进程已经被挂起\n" );//若剩余状态为非0，1，表示已被挂起
        bj=1;break;  //赋值临时变量
        }
      }
    if(bj==0) printf("\n要杀死的进程不存在\n" );
  }


//唤醒进程
void huanxing() {
	if(!shumu)   //首先确定是否存在进程
  { printf("当前没有运行的进程\n");
    return;
  }
  if(guaqi==0)  //判断是否又换出的进程。若挂起值0,表示没有挂起的进程
  { printf("\n当前没有换出的进程\n");
    return;
  }
  printf("\n请输入要唤醒的进程的pid值:\n");
  scanf("%d",&pid);
    for(i=0;i<20;i++){
    if(pid==neicun[i].pid)
    {
          if(neicun[i].zhuangtai==2)//若是挂起状态
           {neicun[i].zhuangtai=1; //将该进程的状态设为1唤醒，进入就绪态
            guaqi--;
            shumu++;
            printf("\n该进程已成功唤醒\n" );
           }
           else if(neicun[i].zhuangtai==0)
           printf("\n要唤醒的进程不存在\n" );
           else printf("\n要唤醒的进程已经在内存中\n" );
         }
    }

}

int main() {
	int n = 1;
	int num;
	//一开始所有进程都不在内存中
	for (int i = 0; i<20; i++)
		neicun[i].zhuangtai = 0;
	while (n) {
		printf("\n********************************************");
		printf("\n*进程演示系统*");
		printf("\n********************************************");
		printf("\n1.创建新的进程 2.查看运行进程");
		printf("\n3.换出某个进程 4.杀死运行进程");
		printf("\n5.唤醒某个进程 6.退出系统");
		printf("\n********************************************");
		printf("\n 请选择(1～6)\n");
		scanf("%d", &num, 1);
		switch (num) {
		case 1: create(); break;
		case 2: run(); break;
		case 3: huanchu(); break;
		case 4: kill(); break;
		case 5: huanxing(); break;
		case 6: exit(0);
		default: n = 0;
		}
		flag = 0;//恢复标记
	}
}
