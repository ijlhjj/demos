/* 斐波那契数列示例 */

#include <stdio.h>

/**
 * 时间复杂度 O(2^n)
 */
int fibonacci1(int n)
{
    if (n <= 2)
        return 1;

    return fibonacci1(n - 1) + fibonacci1(n - 2);
}

/**
 * 双数值计算：线性运行时间 O(N)
 */
int fibonacci2(int n)
{
    if (n <= 2)
        return 1;

    int f1 = 1, f2 = 1;     // 基准值
    double count = n / 2.0; // 循环次数，每次计算两个值，所以循环次数减半
    for (int i = 1; i < count; i++)
    {
        f1 = f1 + f2;
        f2 = f1 + f2;
    }

    return n % 2 == 1 ? f1 : f2;
}

int main(int argc, char const *argv[])
{
    printf("%d\n", fibonacci1(10));
    printf("%d\n", fibonacci2(10));

    return 0;
}
