/* 阶乘示例 */

#include <stdio.h>

/**
 * 求 n 的阶乘
 */
int factorial(int n)
{
    if (n <= 1)
        return 1;

    return n * factorial(n - 1);
}

int main(int argc, char const *argv[])
{
    printf("%d\n", factorial(5));

    return 0;
}
