#include <iostream>
#include <cstring>
#include <algorithm>

using namespace std;

typedef long long LL;

int n;

LL C(int a, int b)
{
    LL res = 1;
    for (int i = a, j = 1; j <= b; i--, j++)
    {
        res = res * i / j;
        if (res > n)
            return res;
    }
    return res;
}

bool check(int k)
{
    LL l = k * 2, r = max((LL)n, l);
    while (l < r)
    {
        LL mid = l + r >> 1;
        if (C(mid, k) >= n)
            r = mid;
        else
            l = mid + 1;
    }
    if (C(r, k) != n)
        return false;

    cout << r * (r + 1) / 2 + k + 1 << endl;

    return true;
}

int main()
{
    cin >> n;
    for (int k = 16;; k--)
        if (check(k))
            break;

    system("pause");
    return 0;
}