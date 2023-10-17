// 要求：采用“多级反馈队列法”调度算法对五个进程进行调度，后继队列中进程能获得的时间片大小是翻倍递增的，队列内部采用FCFS先来先服务原则
//队列调度已经实现，现在只需要把静态动态优先级去除，改成队列式作业
/*改动
去除优先级定义
Init函数、PCB结构：新增三个队列数据结构，用于存储进程
Change函数：每个进程执行一次后放入下一个进程

*/


#include <stdlib.h>
#include <stdio.h>
#include <time.h>

/*常量和状态定义*/
#define PRO_NUM 0x05   //5
#define MAX_TIME 0xFF //255

/*状态宏*/
#define WAIT 0x01 
#define RUN 0x02
#define FINISH 0x03

#define ID_ERROR 0x10
#define MIN_PRIOR 0xFF //255
#define MAX_PRIOR 0x00 //0
#define ZERO 0x00 

// 队列
#define Q1 0x01
#define Q2 0x02
#define Q3 0x03

typedef unsigned int Uint32;

/*进程PCB*/
struct PCB_Info
{
    Uint32 s_id;
    Uint32 s_static_prior;
    Uint32 s_dynamic_prior;
    Uint32 s_start_time;
    Uint32 s_need_time;
    Uint32 s_used_time;
    Uint32 s_state;
    Uint32 s_queue; //分为三个小队列,永远优先执行高优先级队列中的进程,直到队列1没有进程才执行队列2,低优先级可获得更多时间片
    // ChangeProcess，
};

/*进程队列*/
struct PCB_Info process[5];
Uint32 g_time;

/*模拟进程执行函数*/
void Simulator(); //模拟器
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
    system("pause");
    return 0;
}

void Simulator()
{
    Init_Process();
    Run_Process();
    End_Process();
}

void Init_Process()
{
    int i;
    Uint32 id;
    srand((unsigned)time(NULL));
    Init_Queue();
    for (i = 0; i < PRO_NUM; ++i)
    {
        /*在这里修改随机数的范围，建议优先级取值为0到4之间，进程工作总时间为1到10之间*/
        id = Create_Process(rand() % 4, 1 + rand() % 10);
        if (id != ID_ERROR)
        {
            printf("**********************************\n");
            printf("创建进程成功\n");
            printf("进程ID号为:%d\n", id);
            printf("进程的静态优先权为:%d\n", process[id].s_static_prior);
            printf("进程的动态优先权为:%d\n", process[id].s_dynamic_prior);
            printf("进程的到达时间为:%d\n", process[id].s_start_time);
            printf("进程需要时间为:%d\n", process[id].s_need_time);
            printf("进程已用CPU时间为:%d\n", process[id].s_used_time);
            printf("进程的状态为:%d\n", process[id].s_state);
            printf("进程的队列为:Q%d\n", process[id].s_queue);
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
    for (i = 0; i < PRO_NUM; ++i)
    {
        process[i].s_id = i;
        process[i].s_dynamic_prior = MIN_PRIOR;
        process[i].s_need_time = 0;
        process[i].s_start_time = 0;
        process[i].s_static_prior = MIN_PRIOR;
        process[i].s_used_time = 0;
        process[i].s_state = FINISH;
        process[i].s_queue = Q1;
    }
}

Uint32 Create_Process(Uint32 pri, Uint32 needtime)
{
    int i = 0;
    Uint32 id = ID_ERROR;
    for (i = 0; i < PRO_NUM; ++i)
    {
        if (process[i].s_state == FINISH)
        {
            id = process[i].s_id;
            process[i].s_dynamic_prior = MIN_PRIOR;
            process[i].s_need_time = needtime;
            process[i].s_start_time = g_time;
            process[i].s_state = WAIT;
            process[i].s_static_prior = pri;
            process[i].s_used_time = 0x0;
            process[i].s_queue = Q1;
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
    for (i = 0; i < PRO_NUM; ++i)
    {
        printf("%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n", g_time, process[i].s_id, process[i].s_state, process[i].s_used_time, process[i].s_need_time,
               process[i].s_start_time, process[i].s_static_prior, process[i].s_dynamic_prior);
    }
}
Uint32 Get_PriProcess()
{
    Uint32 id = ID_ERROR;
    int i, prev_id = ID_ERROR;
    Uint32 prior = MIN_PRIOR * 2, temp_prior;
    for (i = 0; i < PRO_NUM; ++i)
    {
        if (process[i].s_state != FINISH)
        {
            temp_prior = process[i].s_dynamic_prior + process[i].s_static_prior;
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
    switch (process[id].s_queue)
    {
    case 1:
        g_time += Q1;
        process[id].s_used_time+=Q1;
        break;
    case 2:
        g_time += Q2;
        process[id].s_used_time+=Q2;
        break;
    case 3:
        g_time += Q3;
        process[id].s_used_time+=Q3;
        break;
    }
    process[id].s_state = RUN;
    Print_State();
}

void Change_Process(Uint32 id)
{
    int i;
    if (process[id].s_need_time == process[id].s_used_time)
    {
        process[id].s_state = FINISH;
    }
    else
    {
        //本时间片运行过的进程,变为最低优先级s
        process[id].s_dynamic_prior = MIN_PRIOR;
        process[id].s_state = WAIT;
    }
    //本时间片未运行的程度,优先级值全部--
    for (i = 0; i < PRO_NUM; ++i)
    {
        if ((i != id) && (process[i].s_state != FINISH))
        {
            // process[i].s_dynamic_prior > 0 ? --process[i].s_dynamic_prior : process[i].s_dynamic_prior = ZERO;
            if (process[i].s_dynamic_prior > 0)
                --process[i].s_dynamic_prior;
            else
                process[i].s_dynamic_prior = 0;
        }
    }
}

void End_Process()
{
    printf("所有进程结束状态:\n");
    Print_State();
    printf("所有进程已经结束!\n");
}
