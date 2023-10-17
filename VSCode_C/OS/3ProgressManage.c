// Ҫ�󣺲��á��༶�������з��������㷨��������̽��е��ȣ���̶����н����ܻ�õ�ʱ��Ƭ��С�Ƿ��������ģ������ڲ�����FCFS�����ȷ���ԭ��
//���е����Ѿ�ʵ�֣�����ֻ��Ҫ�Ѿ�̬��̬���ȼ�ȥ�����ĳɶ���ʽ��ҵ
/*�Ķ�
ȥ�����ȼ�����
Init������PCB�ṹ�����������������ݽṹ�����ڴ洢����
Change������ÿ������ִ��һ�κ������һ������

*/


#include <stdlib.h>
#include <stdio.h>
#include <time.h>

/*������״̬����*/
#define PRO_NUM 0x05   //5
#define MAX_TIME 0xFF //255

/*״̬��*/
#define WAIT 0x01 
#define RUN 0x02
#define FINISH 0x03

#define ID_ERROR 0x10
#define MIN_PRIOR 0xFF //255
#define MAX_PRIOR 0x00 //0
#define ZERO 0x00 

// ����
#define Q1 0x01
#define Q2 0x02
#define Q3 0x03

typedef unsigned int Uint32;

/*����PCB*/
struct PCB_Info
{
    Uint32 s_id;
    Uint32 s_static_prior;
    Uint32 s_dynamic_prior;
    Uint32 s_start_time;
    Uint32 s_need_time;
    Uint32 s_used_time;
    Uint32 s_state;
    Uint32 s_queue; //��Ϊ����С����,��Զ����ִ�и����ȼ������еĽ���,ֱ������1û�н��̲�ִ�ж���2,�����ȼ��ɻ�ø���ʱ��Ƭ
    // ChangeProcess��
};

/*���̶���*/
struct PCB_Info process[5];
Uint32 g_time;

/*ģ�����ִ�к���*/
void Simulator(); //ģ����
/*��ʼ��5�����̺���*/
void Init_Process();
/*��ʼ�����̶��к���*/
void Init_Queue();
/*�������̺���*/
Uint32 Create_Process(Uint32 pri, Uint32 needtime);
/*ϵͳ���к���*/
void Run_Process();
/*�õ�������ȼ����� ID����*/
Uint32 Get_PriProcess();
/*����ʱ��Ƭִ�к���*/
void Work_Process(Uint32 id);
/*�ı����״̬�����ȼ�����*/
void Change_Process(Uint32 id);
/*��ӡ����״̬����*/
void Print_State();
/*����ϵͳ����*/
void End_Process();

/*��ں���*/
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
        /*�������޸�������ķ�Χ���������ȼ�ȡֵΪ0��4֮�䣬���̹�����ʱ��Ϊ1��10֮��*/
        id = Create_Process(rand() % 4, 1 + rand() % 10);
        if (id != ID_ERROR)
        {
            printf("**********************************\n");
            printf("�������̳ɹ�\n");
            printf("����ID��Ϊ:%d\n", id);
            printf("���̵ľ�̬����ȨΪ:%d\n", process[id].s_static_prior);
            printf("���̵Ķ�̬����ȨΪ:%d\n", process[id].s_dynamic_prior);
            printf("���̵ĵ���ʱ��Ϊ:%d\n", process[id].s_start_time);
            printf("������Ҫʱ��Ϊ:%d\n", process[id].s_need_time);
            printf("��������CPUʱ��Ϊ:%d\n", process[id].s_used_time);
            printf("���̵�״̬Ϊ:%d\n", process[id].s_state);
            printf("���̵Ķ���Ϊ:Q%d\n", process[id].s_queue);
            printf("\n");
        }
        else
        {
            printf("��������ʧ��\n");
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
    printf("ʱ�� ����ID\t״̬ ����ʱ�� ��Ҫʱ�� ��ʼʱ�� �����ȼ� �����ȼ�\n");
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
        //��ʱ��Ƭ���й��Ľ���,��Ϊ������ȼ�s
        process[id].s_dynamic_prior = MIN_PRIOR;
        process[id].s_state = WAIT;
    }
    //��ʱ��Ƭδ���еĳ̶�,���ȼ�ֵȫ��--
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
    printf("���н��̽���״̬:\n");
    Print_State();
    printf("���н����Ѿ�����!\n");
}
