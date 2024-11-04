/* qsort快速排序函数示例 */

#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

/* 比较函数：
 *
 * 如果首个参数小于第二个，那么返回负整数值；
 * 如果首个参数大于第二个，那么返回正整数值；
 * 如果两个参数等价，那么返回零。
 */
int compare_ints(const void *a, const void *b)
{
    int arg1 = *(const int *)a;
    int arg2 = *(const int *)b;

    if (arg1 < arg2)
        return -1;
    if (arg1 > arg2)
        return 1;
    return 0;

    // return (arg1 > arg2) - (arg1 < arg2); // 可行的简写
    // return arg1 - arg2; // 错误的简写（若给出 INT_MIN 则会失败）
}

int main(void)
{
    int ints[] = {-2, 99, 0, -743, 2, INT_MIN, 4};
    int size = sizeof ints / sizeof *ints; // 计算数组大小

    /* 调用快速排序进行原地排序
     *
     * arg1 - 指向待排序的数组的指针
     * arg2	- 数组的元素数目
     * arg3 - 数组每个元素的字节大小
     * arg4	- 比较函数
     */
    qsort(ints, size, sizeof(int), compare_ints);

    for (int i = 0; i < size; i++)
    {
        printf("%d ", ints[i]);
    }

    printf("\n");
}
