# 《算法精粹》
# 数字字谜问题

from typing import Dict, List, Optional

from csp import Constraint, CSP


class SendMoreMoneyConstraint(Constraint[str, int]):
    def __init__(self, letters: List[str]) -> None:
        super().__init__(letters)
        self.letters = letters

    def satisfied(self, assignment: Dict[str, int]) -> bool:
        # 不同字符不能有相同值
        if len(set(assignment.values())) < len(assignment):
            return False

        # 全部赋值后判断字谜等式
        if len(assignment) == len(self.letters):
            s = assignment["S"]
            e = assignment["E"]
            n = assignment["N"]
            d = assignment["D"]
            m = assignment["M"]
            o = assignment["O"]
            r = assignment["R"]
            y = assignment["Y"]

            send = s * 1000 + e * 100 + n * 10 + d
            more = m * 1000 + o * 100 + r * 10 + e
            money = m * 10000 + o * 1000 + n * 100 + e * 10 + y

            return send + more == money

        return True


if __name__ == "__main__":
    # 变量
    letters = ["S", "E", "N", "D", "M", "O", "R", "Y"]

    # 值域
    possible_digits: Dict[str, List[int]] = {}
    for letter in letters:
        possible_digits[letter] = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    # 和不以 0 为前缀，两个4位数的和 M 只能是 1
    possible_digits["M"] = [1]

    csp = CSP(letters, possible_digits)  # 创建解题框架

    # 添加约束
    csp.add_constraint(SendMoreMoneyConstraint(letters))

    solution: Optional[Dict[str, int]] = csp.backtracking_search()
    if solution is None:
        print("没有找到题解！")
    else:
        print(solution)
