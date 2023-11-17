#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#define MAXSIZE 100

typedef int datatype;
typedef struct
{
    datatype a[MAXSIZE];
    int size;
} bdlay;

void replace(bdlay *lay, datatype x, datatype y)
{
    int i;
    if (lay->size == 0 || !x)
    {
        return;
    }

    for (i = 0; i < lay->size; i++)
    {
        if (lay->a[i] == x)
        {
            lay->a[i] = y;
        }
    }
    return;
}

void test_replace()
{
    bdlay lay;
    lay.size = 5;
    lay.a[0] = 1;
    lay.a[1] = 2;
    lay.a[2] = 3;
    lay.a[3] = 2;
    lay.a[4] = 5;

    replace(&lay, 2, 4);
    for (int i = 0; i < lay.size; i++)
    {
        printf("%d ", lay.a[i]);
    }
}

int main()
{
    test_replace();
    system("pause");
    return 0;
}
