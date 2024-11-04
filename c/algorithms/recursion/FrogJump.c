/* 青蛙跳
 *
 * 一只青蛙可以一次跳 1 级台阶或一次跳 2 级台阶。
 * 例如:
 *     跳上第 1 级台阶只有一种跳法：直接跳 1 级即可。
 *     跳上第 2 级台阶有两种跳法：每次跳 1 级，跳两次；或者一次跳 2 级。
 *     问要跳上第 n 级台阶有多少种跳法？
 */

#include <stdio.h>
#include <stdlib.h>
#include <uthash.h>

/**
 * 跳 n 极台阶的跳法。
 *
 * 自上而下地思考，也就是说如果要跳到 n 级台阶只能从 从 n-1 或 n-2 级跳
 * 由于存在重复计算，时间复杂度为指数级
 */
int frog_jump1(int n)
{
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;

    return frog_jump1(n - 1) + frog_jump1(n - 2);
}

/**
 * 定义哈希表缓存递归中间结果
 */
struct fn
{
    int n;  // key n
    int fn; // value fn
    UT_hash_handle hh;
};
struct fn *fn_map = NULL;

void add_fn(int n, int fn)
{
    struct fn *p = calloc(1, sizeof(struct fn)); // 申请存储空间

    p->n = n;
    p->fn = fn;

    HASH_ADD_INT(fn_map, n, p);
}

struct fn *find_fn(int n)
{
    struct fn *p;

    HASH_FIND_INT(fn_map, &n, p);

    return p;
}

/**
 * 跳 n 极台阶的跳法。
 *
 * 自上而下地思考，也就是说如果要跳到 n 级台阶只能从 从 n-1 或 n-2 级跳
 * 使用哈希表缓存减少重复计算，时间复杂度为 O(n) ，空间复杂度为 O(n)
 */
int frog_jump2(int n)
{
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;

    struct fn *p = find_fn(n);
    if (p != NULL)
        return p->fn;

    int fj1 = frog_jump2(n - 1);
    add_fn(n - 1, fj1);

    int fj2 = frog_jump2(n - 2);
    add_fn(n - 2, fj2);

    return fj1 + fj2;
}

/**
 * 跳 n 极台阶的跳法。
 *
 * 自下而上地计算，时间复杂度为 O(n) ，空间复杂度是 O(1)
 */
int frog_jump3(int n)
{
    if (n == 1)
        return 1;
    if (n == 2)
        return 2;

    int result = 0;
    int pre = 1;
    int next = 2;

    for (int i = 3; i < n + 1; i++)
    {
        result = pre + next;
        pre = next;
        next = result;
    }

    return result;
}

int main(int argc, char const *argv[])
{
    int n = 40;
    printf("%d\n", frog_jump1(n));
    printf("%d\n", frog_jump2(n));
    printf("%d\n", frog_jump3(n));

    return 0;
}
