/* 汉诺塔：时间复杂度为 O(2^n)
 *
 * 有三根相邻的柱子，标号为 A, B, C，A柱子上从下到上按金字塔状叠放着 n 个不同大小的圆盘，要把所有盘子一个一个移动到柱子C上，
 * 并且每次移动同一根柱子上都不能出现大盘子在小盘子上方，请问至少需要多少次移动？
 */

#include <stdio.h>

void move(char a, char b)
{
    printf("%c -> %c\n", a, b);
}

/* 将 n 个圆盘从 A 经由 B 移动到 C 上 */
void hanoi(int n, char a, char b, char c)
{
    if (n <= 0)
        return;

    hanoi(n - 1, a, c, b); // 将上面的  n-1 个圆盘经由 C 移到 B

    move(a, c); // 将 A 移到 C

    hanoi(n - 1, b, a, c); // 再将 B 上的 n-1 个圆盘经由 A 移到 C 上
}

int main(int argc, char const *argv[])
{
    hanoi(3, 'A', 'B', 'C'); // 3个圆盘，从A移动到C，借助于B

    return 0;
}
