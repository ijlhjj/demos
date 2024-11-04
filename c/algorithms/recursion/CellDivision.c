/* 细胞分裂：时间复杂度为 O(2^n)
 *
 * 有一个细胞，每一个小时分裂一次，一次分裂一个子细胞，第三个小时后会死亡。
 * 那么 n 个小时后有多少细胞？
 */

#include <stdio.h>

int aCell(int n);

/**
 * 第 n 小时 b 状态的细胞数
 *
 * fb(1) = 0
 * fb(n) = fa(n-1)
 */
int bCell(int n)
{
    if (n == 1)
        return 0;
    else
        return aCell(n - 1);
}

/**
 * 第 n 小时 c 状态的细胞数
 *
 * fc(1) = fc(2) = 0
 * fc(n) = fb(n-1)
 */
int cCell(int n)
{
    if (n == 1 || n == 2)
        return 0;
    else
        return bCell(n - 1);
}

/**
 * 第 n 小时 a 状态的细胞数
 *
 * fa(1) = 1
 * fa(n) = fa(n-1) + fb(n-1) + fc(n-1)
 */
int aCell(int n)
{
    if (n == 1)
        return 1;
    else
        return aCell(n - 1) + bCell(n - 1) + cCell(n - 1);
}

/* 细胞分裂
 *
 * A 代表分裂细胞的第一小时, B 代表分裂细胞的第二小时, C 代表分裂细胞的第三小时，三个小时后会死亡。
 * f(n) =  fa(n)  + fb(n)  + fc(n)
 */
int division(int n)
{
    return aCell(n) + bCell(n) + cCell(n);
}

int main(int argc, char const *argv[])
{
    printf("%d\n", division(10));

    return 0;
}
