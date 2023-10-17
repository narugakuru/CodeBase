#include <iostream>
#define SIZE 100
using namespace std;

int main()
{
    string s;
    int sign = 0, i = 0;
    cout << "input输入：";
    cin >> s;
    while (s[i++] != NULL)
    {
        cout << s[i];
        if (s[i] = 0)
            sign++;
    }
    // if (sign == 4)
    // s += "0";
    cout << sign;
    system("pause");
}