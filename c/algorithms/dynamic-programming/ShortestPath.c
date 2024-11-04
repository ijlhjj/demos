/**
 * 求三角形的最小路径和
 */

#include <stdio.h>
#include <common.h>

int nums[4][4] = {{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}};
int high = 4;

int traverse(int i, int j)
{
    // 最后一层不再递归，下标 i 要小于 层数减 1
    if (i >= high - 1)
        return 0;

    int leftSum = traverse(i + 1, j) + nums[i + 1][j]; // 递归左子树

    int rightSum = traverse(i + 1, j + 1) + nums[i + 1][j + 1]; // 递归右子树

    return min(leftSum, rightSum); // 取两者较小值
}

/**
 * 递归求解：时间复杂度 O(2^n)
 */
int shortestPath1()
{
    return traverse(0, 0) + nums[0][0];
}

/** 动态规划：时间复杂度 O(n)
 *
 * DP[i,j] = min(DP[i+1, j], D[i+1, j+1]) + triangle[i,j]
 */
int shortestPath2()
{
    // 初始为最后一层节点值
    int mini[] = {4, 1, 8, 3};

    // 从倒数第二行开始计算
    for (int i = 2; i >= 0; i--)
        for (int j = 0; j <= i; j++)
            mini[j] = nums[i][j] + min(mini[j], mini[j + 1]);

    return mini[0];
}

int main(int argc, char const *argv[])
{
    printf("%d\n", shortestPath1());
    printf("%d\n", shortestPath2());

    return 0;
}
