# include <stdio.h>
# include <stdlib.h>
# include <string.h>

//需填充内容： 杀死运行进程；唤醒某个进程



struct jincheng_type {
	int pid;
	int youxian;
	int size;//size
	int status; //标志进程状态，0 为不在内存，1 为在内存，2 为挂起
	char info[10];
};
struct jincheng_type memory[20];
int nums = 0, guaqi = 0, pid, flag = 0;//nums

//创建进程
void create() {
	if (nums >= 20) printf("\n 内存已满，请先换出或杀死进程\n");
	else {
		int i;
		for (i = 0; i<20; i++)
			//定位，找到可以还未创建的进程
			if (memory[i].status == 0) break;

		printf("\n 请输入新进程 pid\n");
		scanf_s("%d", &(memory[i].pid), 5);
        
		for (int j = 0; j < i; j++) {
			if (memory[i].pid == memory[j].pid) {
				printf("\n 该进程已存在\n");
				return;
			}
		}
		printf("\n 请输入新进程优先级\n");
		scanf_s("%d", &(memory[i].youxian), 2);
		printf("\n 请输入新进程大小\n");
		scanf_s("%d", &(memory[i].size), 5);
		printf("\n 请输入新进程内容\n");
		scanf_s("%s", &(memory[i].info), 20);
		//创建进程，使标记位为 1
		memory[i].status = 1;
		nums++;
	}
}

void run() {
	for (int i = 0; i<20; i++) {
		if (memory[i].status == 1) {
			//输出运行进程的各个属性值
			printf("\n pid= %d", memory[i].pid);
			printf(" youxian= %d", memory[i].youxian);
			printf(" size= %d", memory[i].size);
			printf(" status= %d", memory[i].status);
			printf(" info= %s", memory[i].info);
			flag = 1;
		}
	}
	if (!flag) printf("\n 当前没有运行进程\n");
}

//换出进程
void huanchu() {
	if (!nums) {
		printf("当前没有运行进程\n");
		return;
	}
	printf("\n 输入换出进程的 ID 值");
	scanf_s("%d", &pid, 5);
	for (int i = 0; i<20; i++) {
		//定位，找到所要换出的进程，根据其状态做相应处理
		if (pid == memory[i].pid) {
			if (memory[i].status == 1) {
				memory[i].status = 2;
				guaqi++;
				printf("\n 已经成功换出进程\n");
			}
			else if (memory[i].status == 0) printf("\n 要换出的进程不存在\n");
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
	printf("功能未实现，需要自己完成\n");
}

//唤醒进程
void huanxing() {
	printf("功能未实现，需要自己完成\n");
}

void main() {
	int n = 1;
	int num;
	//一开始所有进程都不在内存中
	for (int i = 0; i<20; i++)
		memory[i].status = 0;
	while (n) {
		printf("\n********************************************");
		printf("\n*进程演示系统*");
		printf("\n********************************************");
		printf("\n1.创建新的进程 2.查看运行进程");
		printf("\n3.换出某个进程 4.杀死运行进程");
		printf("\n5.唤醒某个进程 6.退出系统");
		printf("\n********************************************");
		printf("\n 请选择(1～6)\n");
		scanf_s("%d", &num, 1);
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
