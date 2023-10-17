package Test15;
//AABB去掉B，AAA，去掉A
//输入：2，helllo，wooooooow
//输出：hello，woow

import java.util.Scanner;

public class automaton {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入待检验字符串数量");
//        int num = sc.nextInt();
        int num = 1;//一个测试字符串
        while (num-- > 0) {
//            String str = sc.next("输入待检验字符串");
            String str = "helllo";//目标字符串

            int flag = 0;//标记状态
            char cur;//当前字符
            char last = str.charAt(0);//前一个字符（第一个字符）
            String str2 = String.valueOf(str.charAt(0));//拼接字符串

            for (int i = 1; i < str.length(); i++) {
                cur = str.charAt(i);//获取当前字符
                switch (flag) {
                    case 0:
                        if (cur == last) {
                            flag = 1;//进入AA
                        } else {
                            flag = 0;//什么都不做
                        }
                        break;

                    case 1:
                        if (cur == last) {//执行AAA模式
                            continue;//去除AAA中的一个A
                        } else {
                            flag = 2;//进入AABB模式
                        }
                        break;
                    case 2:
                        if (cur == last) {//执行AABB模式
                            //去除AABB中的一个B
                            continue;
                        } else {
                            flag = 0;//结果为AABC，回到初态
                        }
                        break;
                }
                str2 = str2.concat(String.valueOf(cur));
                last = cur;
            }
            System.out.println(str2);

        }

    }
}

/*
* #include<iostream>
#include<string>
using namespace std;

int main() {
	//自动机
	int n;
	cin >> n;

	while (n--) {
		int state = 0;//初始化为状态0
		char cur;//当前字符
		string str;//目标字符串

		cin >> str;
		char last = str[0];//初始化为第一个字符

		string ans = "";
		ans += str[0];//初始化

		for (int i = 1; i < str.size(); ++i) {//开始
			cur = str[i];
			switch (state)
			{
			case 0:
			{
				if (cur == last)//如果是相等的，进入状态1，否则继续状态0；
					state = 1;	//进入状态1：AA形式
				else state = 0; //继续状态0：AB形式，即正常形式
				break;
			}
			case 1:
			{
				if (cur == last)
					continue;//AAA,忽略即可
				else
					state = 2;//进入状态3：AAB形式
				break;
			}
			case 2:
			{
				if (cur == last)
					continue;//AABB，忽略即可
				else
					state = 0;//AABC，就是状态0
				break;
			}
			default:
				break;
			}
			ans = ans + cur;
			last = cur;
		}
		cout << ans << endl;
	}
	return 0;
}
* */