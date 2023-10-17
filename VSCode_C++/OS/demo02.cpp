/*
进程调度算法说明：采用最高优先数的调度算法（即把处理机分配给优先数最高的进程）。

每个进程有一个进程控制块（PCB）表示。进程控制块可以包含如下信息：进程名、优先数、到达时间、需要运行时间、已用CPU时间、进程状态等等。

进程的优先数及需要的运行时间可以事先人为的指定（也可以由随机数产生）。

进程的运行时间以时间片为单位进行计算。

每个进程状态可以是就绪W（Wait）、运行R（Run）、或完成F（Finish）三种状态之一。

就绪进程获得CPU后都只能运行一个时间片。用已占用CPU时间加1表示。

如果运行一个时间片后，进程的已占用CPU时间已达到所需要的运行时间，则撤销该进程，如果运行一个时间片后，进程的已占用CPU时间还未达到所需要的运行时间，

也就是进程还需要继续运行，此时应该将进程的优先数减1（即降低一级），然后把它插入就绪队列等待CPU。

每进行一次调度程序都打印一次运行进程、就绪队列、以及各个进程的PCB，以便进行检查。

重复以上过程，直到所要的进程都完成为止。

 */
#include <stdlib.h>
#include <stdio.h>
#include <time.h>

/*常量和状态定义*/
#define        PRO_NUM     0x05
#define        MAX_TIME    0xFF

/*状态宏*/
#define        WAIT        0x01
#define        RUN         0x02
#define        FINISH      0x03

#define        ID_ERROR     0x10
#define        MIN_PRIOR    0xFF
#define        MAX_PRIOR    0x00

typedef unsigned int    Uint32;

/*进程控制块PCB*/
struct PCB_Info
{
	Uint32    s_id;
	Uint32    s_static_prior;
	Uint32    s_dynamic_prior;
	Uint32    s_start_time;
	Uint32    s_need_time;
	Uint32    s_used_time;
	Uint32    s_state;
};

/*进程队列*/
PCB_Info    g_queue[5];
Uint32      g_time;

/*模拟进程执行函数*/
void Simulator();

/*初始化5个进程函数*/
void Init_Process();

/*初始化进程队列函数*/
void Init_Queue();

/*创建进程函数*/
Uint32 Create_Process(Uint32 pri, Uint32 needtime);

/*系统运行函数*/
void Run_Process();

/*得到最高优先级进程 ID函数*/
Uint32 Get_PriProcess();

/*进程时间片执行函数*/
void Work_Process(Uint32 id);

/*改变进程状态和优先级函数*/
void Change_Process(Uint32 id);

/*打印进程状态函数*/
void Print_State();

/*结束系统函数*/
void End_Process();

/*入口函数*/

int main(int argc, char *argv[])
{
	Simulator();
	return 0;
}

void Simulator()  /*模拟进程执行函数*/
{
	Init_Process();     /*初始化进程队列函数*/
	Run_Process();
	End_Process();
}

void Init_Process()
{
	int i;
	Uint32 id;
	srand((unsigned)time(NULL));   //srand函数是随机数发生器的初始化函数。
	Init_Queue();
	for (i = 0; i<PRO_NUM; ++i)
	{
		/*在这里修改随机数的范围，建议优先级取值为0到4之间，进程工作总时间为1到10之间*/
		id = Create_Process(rand() % 4, 1 + rand() % 10);
		if (id != ID_ERROR)
		{
			printf("**********************************\n");
			printf("创建进程成功\n");
			printf("进程ID号为:%d\n", id);
			printf("进程的静态优先权为:%d\n", g_queue[id].s_static_prior);
			printf("进程的动态优先权为:%d\n", g_queue[id].s_dynamic_prior);
			printf("进程的到达时间为:%d\n", g_queue[id].s_start_time);
			printf("进程需要时间为:%d\n", g_queue[id].s_need_time);
			printf("进程已用CPU时间为:%d\n", g_queue[id].s_used_time);
			printf("进程的状态为:%d\n", g_queue[id].s_state);
			printf("\n");
		}
		else
		{
			printf("创建进程失败\n");
		}
	}
}

void Init_Queue()
{
	int i;
	for (i = 0; i<PRO_NUM; ++i)
	{
		g_queue[i].s_id = i;
		g_queue[i].s_dynamic_prior = MIN_PRIOR;
		g_queue[i].s_need_time = 0;
		g_queue[i].s_start_time = 0;
		g_queue[i].s_static_prior = MIN_PRIOR;
		g_queue[i].s_used_time = 0;
		g_queue[i].s_state = FINISH;
	}
}

Uint32 Create_Process(Uint32 pri, Uint32 needtime)
{
	int i = 0;
	Uint32 id = ID_ERROR;
	for (i = 0; i<PRO_NUM; ++i)
	{
		if (g_queue[i].s_state == FINISH)
		{
			id = g_queue[i].s_id;
			g_queue[i].s_dynamic_prior = MIN_PRIOR;
			g_queue[i].s_need_time = needtime;
			g_queue[i].s_start_time = g_time;
			g_queue[i].s_state = WAIT;
			g_queue[i].s_static_prior = pri;
			g_queue[i].s_used_time = 0x0;
			break;
		}
	}
	return id;
}

void Run_Process()
{
	Uint32 id;
	while ((id = Get_PriProcess()) != ID_ERROR)
	{
		Work_Process(id);
		Change_Process(id);
	}
}

void Print_State()
{
	int i;
	printf("时间 进程ID\t状态 已用时间 需要时间 开始时间 静优先级 动优先级\n");
	for (i = 0; i<PRO_NUM; ++i)
	{
		printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n", g_time, g_queue[i].s_id, g_queue[i].s_state, g_queue[i].s_used_time, g_queue[i].s_need_time,
			g_queue[i].s_start_time, g_queue[i].s_static_prior, g_queue[i].s_dynamic_prior);
	}
}
Uint32 Get_PriProcess()
{
	Uint32 id = ID_ERROR;
	int i, prev_id = ID_ERROR;
	Uint32 prior = MIN_PRIOR * 2, temp_prior;
	for (i = 0; i<PRO_NUM; ++i)
	{
		if (g_queue[i].s_state != FINISH)
		{
			temp_prior = g_queue[i].s_dynamic_prior + g_queue[i].s_static_prior;
			if (temp_prior <= prior)
			{
				id = i;
				prior = temp_prior;
			}
		}
	}
	return id;
}

void Work_Process(Uint32 id)
{
	++g_time;
	g_queue[id].s_state = RUN;
	++g_queue[id].s_used_time;
	Print_State();
}

void Change_Process(Uint32 id)
{
	int i;
	if (g_queue[id].s_need_time == g_queue[id].s_used_time)
	{
		g_queue[id].s_state = FINISH;

	}
	else
	{
		g_queue[id].s_dynamic_prior = MIN_PRIOR;
		g_queue[id].s_state = WAIT;

	}
	for (i = 0; i<PRO_NUM; ++i)
	{
		if ((i != id) && (g_queue[i].s_state != FINISH))
		{
			g_queue[i].s_dynamic_prior>0 ? --g_queue[i].s_dynamic_prior : g_queue[i].s_dynamic_prior = 0;
		}
	}
}

void End_Process()
{
	printf("所有进程结束状态:\n");
	Print_State();
	printf("所有进程已经结束!\n");
}
