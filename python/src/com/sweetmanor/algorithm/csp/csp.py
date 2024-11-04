# 《算法精粹》
# 约束满足问题

from abc import ABC, abstractmethod
from typing import TypeVar, Generic, List, Dict, Optional

V = TypeVar('V')
D = TypeVar('D')


class Constraint(Generic[V, D], ABC):
    def __init__(self, variables: List[V]) -> None:
        self.variables = variables  # 变量

    @abstractmethod
    def satisfied(self, assignment: Dict[V, D]) -> bool:
        """ 校验是否满足约束条件 """
        ...


class CSP(Generic[V, D]):

    def __init__(self, variables: List[V], domains: Dict[V, List[D]]) -> None:
        self.variables = variables  # 变量
        self.domains = domains  # 值域
        self.constraints: Dict[V, List[Constraint[V, D]]] = {}  # 约束

        # 每个变量默认添加一个空的约束列表
        for var in self.variables:
            # 校验变量必须在值域字典中
            if var not in self.domains:
                raise LookupError("Every variable should have a domain assigned to it.")
            self.constraints[var] = []

    def add_constraint(self, constraint: Constraint[V, D]) -> None:
        """ 增加约束 """
        for var in constraint.variables:
            # 新增约束的变量必须包含在当前变量列表中
            if var not in self.variables:
                raise LookupError("Variable in constraint not in CSP")
            self.constraints[var].append(constraint)  # 约束添加到约束字典

    def consistent(self, variable: V, assignment: Dict[V, D]) -> bool:
        """ 检查当前赋值是否满足所有约束 """
        for constraint in self.constraints[variable]:
            # 校验是否满足约束条件
            if not constraint.satisfied(assignment):
                return False
        return True

    def backtracking_search(self, assignment: Dict[V, D] = {}) -> Optional[Dict[V, D]]:
        """ 递归回溯搜索 """

        # 所有变量已赋值，完成搜索
        if len(assignment) == len(self.variables):
            return assignment

        # 找一个未赋值的变量
        unassigned = [v for v in self.variables if v not in assignment]
        first = unassigned[0]

        # 尝试变量所有可能赋值
        for val in self.domains[first]:
            # 创建副本，并增加当前赋值
            local_assignment = assignment.copy()
            local_assignment[first] = val

            # 检查当前赋值是否满足约束条件
            if self.consistent(first, local_assignment):
                result = self.backtracking_search(local_assignment)  # 递归求解
                # 找到满足约束的解
                if result is not None:
                    return result

        # 没有找到解时返回 None
        return None
