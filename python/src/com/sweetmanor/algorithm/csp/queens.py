# 《算法精粹》
# 八皇后问题

from typing import Dict, List, Optional

from csp import Constraint, CSP


class QueensConstraint(Constraint[int, int]):
    def __init__(self, columns: List[int]) -> None:
        super().__init__(columns)
        self.columns = columns

    def satisfied(self, assignment: Dict[int, int]) -> bool:
        # 检查当前所有参数赋值
        for q1c, q1r in assignment.items():
            for q2c in range(q1c + 1, len(self.columns) + 1):
                # 当前列已赋值
                if q2c in assignment:
                    q2r = assignment[q2c]
                    # 同一行
                    if q1r == q2r:
                        return False
                    # 同一斜线
                    if abs(q1r - q2r) == abs(q1c - q2c):
                        return False
        # 不违反约束
        return True


if __name__ == "__main__":
    # 变量为8列
    columns = [1, 2, 3, 4, 5, 6, 7, 8]

    # 每个变量的值域都是8行
    rows: Dict[int, List[int]] = {}
    for col in columns:
        rows[col] = [1, 2, 3, 4, 5, 6, 7, 8]

    csp = CSP(columns, rows)  # 创建解题框架

    # 添加约束
    csp.add_constraint(QueensConstraint(columns))

    solution: Optional[Dict[int, int]] = csp.backtracking_search()
    if solution is None:
        print("没有找到题解！")
    else:
        print(solution)
