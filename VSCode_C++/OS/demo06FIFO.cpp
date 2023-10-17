#include <iostream>
#include <stdio.h>
#include <iomanip>
using namespace std;

const int DataMax = 100;
const int BlockNum = 10;
int DataShow[BlockNum][DataMax];        // 用于存储要显示的数组
bool DataShowEnable[BlockNum][DataMax]; // 用于存储数组中的数据是否需要显示
int Data[DataMax];                      // 保存数据
int Block[BlockNum];                    // 物理块
int count[BlockNum];                    // 计数器
int N;                                  // 页面个数
int M;                                  //最小物理块数
int ChangeTimes;

void DataInput(); // 输入数据的函数
void DataOutput();
void LRU(); // LRU函数

///*
int main(int argc, char *argv[])
{
    DataInput(); //  DataInput();
    LRU();
    system("pause");
    return 0;
}
//*/
void DataInput()
{

    cout << "请输入最小物理块数：";
    cin >> M;
    while (M > BlockNum) // 大于数据个数
    {
        cout << "物理块数超过预定值，请重新输入：";
        cin >> M;
    }
    cout << "请输入页面的个数：";
    cin >> N;
    while (N > DataMax) // 大于数据个数
    {
        cout << "页面个数超过预定值，请重新输入：";
        cin >> N;
    }
    cout << "请输入页面访问序列：" << endl;
    for (int i = 0; i < N; i++)
        cin >> Data[i];
}
void DataOutput()
{
    int i, j;
    for (i = 0; i < N; i++) // 对所有数据操作
    {
        cout << Data[i] << "  ";
    }
    cout << "\n--------------------------------" << endl;
    for (j = 0; j < M; j++)
    {
        cout << " ";
        for (i = 0; i < N; i++) // 对所有数据操作
        {
            if (DataShowEnable[j][i])
                cout << DataShow[j][i] << " | ";
            else
                cout << "  | ";
        }
        cout << endl;
    }
    cout << "\n缺页次数: " << ChangeTimes << endl;
    cout << "缺页率: " << ChangeTimes * 100 / N << "%" << endl;
}

void LRU()
{
    int i, j;
    bool find;
    int point;
    int temp; // 临时变量
    ChangeTimes = 0;
    for (j = 0; j < M; j++)
        for (i = 0; i < N; i++)
            DataShowEnable[j][i] = false; // 初始化为false，表示没有要显示的数据
    for (i = 0; i < M; i++)
    {
        count[i] = 0;
    }
    for (i = 0; i < N; i++) // 对有所数据操作
    {
        // 增加count
        for (j = 0; j < M; j++)
            count[j]++;
        find = false; // 表示块中有没有该数据
        for (j = 0; j < M; j++)
        {
            if (Block[j] == Data[i])
            {
                count[j] = 0;
                find = true;
            }
        }
        if (find)
            continue; // 块中有该数据，判断下一个数据
        // 块中没有该数据
        ChangeTimes++;   // 缺页次数++
        if ((i + 1) > M) // 因为i是从0开始记，而BlockNum指的是个数，从1开始，所以i+1
        {
            //获得要替换的块指针
            temp = 0;
            for (j = 0; j < M; j++)
            {
                if (temp < count[j])
                {
                    temp = count[j];
                    point = j; // 获得离的最远的指针
                }
            }
        }
        else
            point = i;
        // 替换
        Block[point] = Data[i];
        count[point] = 0;
        // 保存要显示的数据
        for (j = 0; j < M; j++)
        {
            DataShow[j][i] = Block[j];
            DataShowEnable[i < M ? (j <= i ? j : i) : j][i] = true; // 设置显示数据
        }
    }
    // 输出信息
    cout << endl;
    cout << "内存状态：\n"
         << "--------------------------------" << endl;
    DataOutput();
}
