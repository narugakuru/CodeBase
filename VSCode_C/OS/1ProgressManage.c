#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//��������ݣ� ɱ�����н��̣�����ĳ������

//��չ���ݣ���ÿ�����̷����ڴ棬���̴���ʱӦ���Ǿ���״̬�����ȼ������������

int mSize = 20;

struct progress
{
    int pid;
    int priority;
    int size;   //size
    int status; //��־����״̬��0 Ϊ�����ڴ棬1 Ϊ���ڴ棬2 Ϊ����
    char info[10];
};
struct progress memory[20];
int nums = 0, guaqi = 0, pid, flag = 0; //nums

//��������
void create()
{
    if (nums >= 20)
        printf("\n �ڴ����������Ȼ�����ɱ������\n");
    else
    {
        int i;
        for (i = 0; i < 20; i++)
            //��λ���ҵ����Ի�δ�����Ľ���
            if (memory[i].status == 0)
                break;

        printf("\n �������½��� pid\n");
        scanf_s("%d", &(memory[i].pid), 5);

        //���pid�ظ�
        for (int j = 0; j < i; j++)
        {
            if (memory[i].pid == memory[j].pid)
            {
                printf("\n �ý����Ѵ���\n");
                return;
            }
        }
        printf("\n �������½������ȼ�1~3\n");
        scanf_s("%d", &(memory[i].priority), 2);
        printf("\n �������½��̴�С0~20\n");
        scanf_s("%d", &(memory[i].size), 5);
        printf("\n �������½������ݣ�10���ַ�\n");
        scanf_s("%s", &(memory[i].info), 20);
        //�������̣�ʹ���λΪ 1
        memory[i].status = 1;
        nums += memory[i].size; //�ڴ�����
    }
}

void run()
{
    for (int i = 0; i < 20; i++)
    {
        if (memory[i].status == 1)
        {
            //������н��̵ĸ�������ֵ
            printf("\n pid= %d", memory[i].pid);
            printf(" priority= %d", memory[i].priority);
            printf(" size= %d", memory[i].size);
            printf(" status= %d", memory[i].status);
            printf(" info= %s", memory[i].info);
            flag = 1;
        }
    }
    if (!flag)
        printf("\n ��ǰû�����н���\n");
}

//��������
void huanchu()
{
    if (!nums)
    {
        printf("��ǰû�����н���\n");
        return;
    }
    printf("\n ���뻻�����̵� ID ֵ");
    scanf_s("%d", &pid, 5);
    for (int i = 0; i < 20; i++)
    {
        //��λ���ҵ���Ҫ�����Ľ��̣�������״̬����Ӧ����
        if (pid == memory[i].pid)
        {
            if (memory[i].status == 1)
            {
                memory[i].status = 2;
                guaqi++;
                printf("\n �Ѿ��ɹ���������\n");
            }
            else if (memory[i].status == 0)
                printf("\n Ҫ�����Ľ��̲�����\n");
            else
                printf("\n Ҫ�����Ľ����ѱ�����\n");
            flag = 1;
            break;
        }
    }
    //�Ҳ�������˵�����̲�����
    if (flag == 0)
        printf("\n Ҫ�����Ľ��̲�����\n");
}

//ɱ������
void kill()
{
    printf("������Ҫɱ���Ľ���pid");
    scanf_s("%d", &pid);

    int i;
    //����pid
    for (i = 0; i < 20; i++)
    {
        memory[i].pid == pid;
        break;
        if (i == 20)
        {
            printf("pid�����ڣ�");
            return;
        }
    }

    //���kill���̵��ڴ�
    memset(&memory[i], 0, sizeof(struct progress));
    // memory[i].pid = NULL;
    // memory[i].priority = NULL;
    // memory[i].size = NULL;
    // memory[i].info = NULL;

    //���Ϊ0
    memory[i].status = 0;
    printf("��ɱ��pidΪ%d�Ľ���\n", pid);
}

//���ѽ���
void huanxing()
{
    if (!nums)
    {
        printf("��ǰû�����н���\n");
        return;
    }
    printf("\n ���뻻�����̵� ID ֵ");
    scanf_s("%d", &pid, 5);

    for (int i = 0; i < 20; i++)
    {
        //����pid
        if (pid == memory[i].pid)
        {
            if (memory[i].status == 2)
            {
                memory[i].status = 1;
                printf("\n �Ѿ��ɹ����ѽ���\n");
            }
            else if (memory[i].status == 0)
                printf("\n Ҫ���ѵĽ��̲�����\n");
            else
                printf("\n Ҫ���ѵĽ���δ�����𣬲���Ҫ����\n");
            flag = 1;
            break;
        }
    }

    if (flag == 0)
        printf("���ѽ���ʧ��\n");
}
//�������ȼ�����
void priority()
{
    if (!nums)
    {
        printf("��ǰû�����н���\n");
        return;
    }

    //��ӡ�������ȼ�
    for (int i = 0; i < 20; i++)
    {
        if (memory[i].status == 1)
        {
            //������н��̵ĸ�������ֵ
            printf("\n pid= %d", memory[i].pid);
            printf(" priority= %d", memory[i].priority);
        }
    }

    printf("�������޸����ȼ����̵�pid\n");
    scanf_s("%d", &pid, 5);

    int i;
    //��ӡ�������ȼ�
    for (i = 0; i < 20; i++)
    {
        if (memory[i].pid == pid)
        {
            printf("���������ȼ���\n");
            scanf_s("%d", &memory[i].priority);
        }
        if (i == 20)
        {
            printf("pid������\n");
            return;
        }
    }
    printf("����%d�����ȼ����޸�Ϊ%d", memory[i].pid, memory[i].priority);
}



void main()
{
    int n = 1;
    int num;
    //һ��ʼ���н��̶������ڴ���
    for (int i = 0; i < 20; i++)
        memory[i].status = 0;
    while (n)
    {
        printf("\n********************************************");
        printf("\n*������ʾϵͳ*");
        printf("\n********************************************");
        printf("\n1.�����µĽ��� 2.�鿴���н���");
        printf("\n3.����ĳ������ 4.ɱ�����н���");
        printf("\n5.����ĳ������ 6.�˳�ϵͳ");
        printf("\n7.���ȼ�����");
        printf("\n********************************************");
        printf("\n ��ѡ��(1��6)\n");
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
        flag = 0; //�ָ����
    }
}
