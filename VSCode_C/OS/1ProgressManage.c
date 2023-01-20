#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//需填充内容： 杀死运行进程；唤醒某个进程

//拓展内容：给每个进程分配内存，进程创建时应该是就绪状态，优先级管理，多核运行

int mSize = 20;

struct progress
{
    int pid;
    int priority;
    int size;   //size
    int status; //标志进程状态，0 为不在内存，1 为在内存，2 为挂起
    char info[10];
};
struct progress memory[20];
int nums = 0, guaqi = 0, pid, flag = 0; //nums

//创建进程
void create()
{
    if (nums >= 20)
        printf("\n 内存已满，请先换出或杀死进程\n");
    else
    {
        int i;
        for (i = 0; i < 20; i++)
            //定位，找到可以还未创建的进程
            if (memory[i].status == 0)
                break;

        printf("\n 请输入新进程 pid\n");
        scanf_s("%d", &(memory[i].pid), 5);

        //检查pid重复
        for (int j = 0; j < i; j++)
        {
            if (memory[i].pid == memory[j].pid)
            {
                printf("\n 该进程已存在\n");
                return;
            }
        }
        printf("\n 请输入新进程优先级1~3\n");
        scanf_s("%d", &(memory[i].priority), 2);
        printf("\n 请输入新进程大小0~20\n");
        scanf_s("%d", &(memory[i].size), 5);
        printf("\n 请输入新进程内容：10个字符\n");
        scanf_s("%s", &(memory[i].info), 20);
        //创建进程，使标记位为 1
        memory[i].status = 1;
        nums += memory[i].size; //内存增加
    }
}

void run()
{
    for (int i = 0; i < 20; i++)
    {
        if (memory[i].status == 1)
        {
            //输出运行进程的各个属性值
            printf("\n pid= %d", memory[i].pid);
            printf(" priority= %d", memory[i].priority);
            printf(" size= %d", memory[i].size);
            printf(" status= %d", memory[i].status);
            printf(" info= %s", memory[i].info);
            flag = 1;
        }
    }
    if (!flag)
        printf("\n 当前没有运行进程\n");
}

//换出进程
void huanchu()
{
    if (!nums)
    {
        printf("当前没有运行进程\n");
        return;
    }
    printf("\n 输入换出进程的 ID 值");
    scanf_s("%d", &pid, 5);
    for (int i = 0; i < 20; i++)
    {
        //定位，找到所要换出的进程，根据其状态做相应处理
        if (pid == memory[i].pid)
        {
            if (memory[i].status == 1)
            {
                memory[i].status = 2;
                guaqi++;
                printf("\n 已经成功换出进程\n");
            }
            else if (memory[i].status == 0)
                printf("\n 要换出的进程不存在\n");
            else
                printf("\n 要换出的进程已被挂起\n");
            flag = 1;
            break;
        }
    }
    //找不到，则说明进程不存在
    if (flag == 0)
        printf("\n 要换出的进程不存在\n");
}

//杀死进程
void kill()
{
    printf("请输入要杀死的进程pid");
    scanf_s("%d", &pid);

    int i;
    //查找pid
    for (i = 0; i < 20; i++)
    {
        memory[i].pid == pid;
        break;
        if (i == 20)
        {
            printf("pid不存在！");
            return;
        }
    }

    //清空kill进程的内存
    memset(&memory[i], 0, sizeof(struct progress));
    // memory[i].pid = NULL;
    // memory[i].priority = NULL;
    // memory[i].size = NULL;
    // memory[i].info = NULL;

    //标记为0
    memory[i].status = 0;
    printf("已杀死pid为%d的进程\n", pid);
}

//唤醒进程
void huanxing()
{
    if (!nums)
    {
        printf("当前没有运行进程\n");
        return;
    }
    printf("\n 输入换出进程的 ID 值");
    scanf_s("%d", &pid, 5);

    for (int i = 0; i < 20; i++)
    {
        //查找pid
        if (pid == memory[i].pid)
        {
            if (memory[i].status == 2)
            {
                memory[i].status = 1;
                printf("\n 已经成功唤醒进程\n");
            }
            else if (memory[i].status == 0)
                printf("\n 要唤醒的进程不存在\n");
            else
                printf("\n 要唤醒的进程未被挂起，不需要唤醒\n");
            flag = 1;
            break;
        }
    }

    if (flag == 0)
        printf("唤醒进程失败\n");
}
//进程优先级管理
void priority()
{
    if (!nums)
    {
        printf("当前没有运行进程\n");
        return;
    }

    //打印进程优先级
    for (int i = 0; i < 20; i++)
    {
        if (memory[i].status == 1)
        {
            //输出运行进程的各个属性值
            printf("\n pid= %d", memory[i].pid);
            printf(" priority= %d", memory[i].priority);
        }
    }

    printf("请输入修改优先级进程的pid\n");
    scanf_s("%d", &pid, 5);

    int i;
    //打印进程优先级
    for (i = 0; i < 20; i++)
    {
        if (memory[i].pid == pid)
        {
            printf("请输入优先级：\n");
            scanf_s("%d", &memory[i].priority);
        }
        if (i == 20)
        {
            printf("pid不存在\n");
            return;
        }
    }
    printf("进程%d的优先级已修改为%d", memory[i].pid, memory[i].priority);
}



void main()
{
    int n = 1;
    int num;
    //一开始所有进程都不在内存中
    for (int i = 0; i < 20; i++)
        memory[i].status = 0;
    while (n)
    {
        printf("\n********************************************");
        printf("\n*进程演示系统*");
        printf("\n********************************************");
        printf("\n1.创建新的进程 2.查看运行进程");
        printf("\n3.换出某个进程 4.杀死运行进程");
        printf("\n5.唤醒某个进程 6.退出系统");
        printf("\n7.优先级管理");
        printf("\n********************************************");
        printf("\n 请选择(1～6)\n");
        scanf_s("%d", &num, 1);
        switch (num)
        {
        case 1:
            create();
            break;
        case 2:
            run();
            break;
        case 3:
            huanchu();
            break;
        case 4:
            kill();
            break;
        case 5:
            huanxing();
            break;
        case 6:
            exit(0);
        default:
            n = 0;
        }
        flag = 0; //恢复标记
    }
}
