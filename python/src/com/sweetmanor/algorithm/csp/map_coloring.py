# 《算法精粹》
# 地图着色问题

from typing import Dict, List, Optional

from csp import Constraint, CSP


class MapColoringConstraint(Constraint[str, str]):
    def __init__(self, place1: str, place2: str) -> None:
        super().__init__([place1, place2])
        self.place1 = place1  # 两个相邻的地区
        self.place2 = place2

    def satisfied(self, assignment: Dict[str, str]) -> bool:
        # 如果有未着色地区，不会违反约束
        if self.place1 not in assignment or self.place2 not in assignment:
            return True
        # 两个地区着色不同，满足约束
        return assignment[self.place1] != assignment[self.place2]


if __name__ == "__main__":
    # 变量为7个地区
    variables = ["Western Australia", "Northern Territory", "South Australia",
                 "Queensland", "New South Wales", "Victoria", "Tasmania"]

    # 每个变量的值域都是红、绿、蓝三色
    domains: Dict[str, List[str]] = {}
    for variable in variables:
        domains[variable] = ["red", "green", "blue"]

    csp = CSP(variables, domains)  # 创建解题框架

    # 添加约束，每对相邻地区为一个约束
    csp.add_constraint(MapColoringConstraint("Western Australia", "Northern Territory"))
    csp.add_constraint(MapColoringConstraint("Western Australia", "South Australia"))
    csp.add_constraint(MapColoringConstraint("South Australia", "Northern Territory"))
    csp.add_constraint(MapColoringConstraint("Queensland", "Northern Territory"))
    csp.add_constraint(MapColoringConstraint("Queensland", "South Australia"))
    csp.add_constraint(MapColoringConstraint("Queensland", "New South Wales"))
    csp.add_constraint(MapColoringConstraint("New South Wales", "South Australia"))
    csp.add_constraint(MapColoringConstraint("Victoria", "South Australia"))
    csp.add_constraint(MapColoringConstraint("Victoria", "New South Wales"))
    csp.add_constraint(MapColoringConstraint("Victoria", "Tasmania"))

    solution: Optional[Dict[str, str]] = csp.backtracking_search()
    if solution is None:
        print("没有找到题解！")
    else:
        print(solution)
