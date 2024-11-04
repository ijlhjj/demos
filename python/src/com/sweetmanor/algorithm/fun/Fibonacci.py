# 《算法精粹》
# 斐波那契序列示例

from functools import lru_cache
from typing import Dict
from typing import Generator


def fib2(n: int) -> int:
    # 普通递归算法
    if n < 2:
        return n

    return fib2(n - 1) + fib2(n - 2)


# 使用字典作为结果缓存
memo: Dict[int, int] = {0: 0, 1: 1}


def fib3(n: int) -> int:
    # 使用缓存优化递归调用
    if n not in memo:
        memo[n] = fib3(n - 1) + fib3(n - 2)
    return memo[n]


@lru_cache(maxsize=None)
def fib4(n: int) -> int:
    # 内置装饰器缓存
    if n < 2:
        return n

    return fib4(n - 1) + fib4(n - 2)


def fib5(n: int) -> int:
    # 循环算法
    if n == 0:
        return 0

    last: int = 0  # fib(0)
    next: int = 1  # fib(1)

    for _ in range(1, n):
        last, next = next, last + next

    return next


def fib6(n: int) -> Generator[int, None, None]:
    yield 0

    if n > 0:
        yield 1

    last: int = 0  # fib(0)
    next: int = 1  # fib(1)

    for _ in range(1, n):
        last, next = next, last + next
        yield next


if __name__ == "__main__":
    N = 20

    print("斐波那契数列：")

    # 递归 fib2：40以内可以秒出
    for i in range(N):
        print(fib2(i), end="  ")
    print()

    # 缓存递归 fib3
    for i in range(N):
        print(fib3(i), end="  ")
    print()

    # 内置装饰器缓存 fib4
    for i in range(N):
        print(fib4(i), end="  ")
    print()

    # 循环算法 fib5
    for i in range(N):
        print(fib5(i), end="  ")
    print()

    # 生成器模式 fib6
    for i in fib6(N):
        print(i, end="  ")
    print()
