# 《算法精粹》
# 计算 π
# π = 4/1 − 4/3 + 4/5 − 4/7 + 4/9 − 4/11 ……

def calculate_pi(n_terms: int) -> float:
    num: float = 4
    denominator: float = 1.0
    operation: float = 1.0

    pi: float = 0.0
    for _ in range(n_terms):
        pi += operation * (num / denominator)
        denominator += 2.0
        operation = -operation

    return pi


if __name__ == '__main__':
    print(calculate_pi(1000000))
