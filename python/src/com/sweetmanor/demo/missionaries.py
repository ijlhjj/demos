# 《算法精粹》
# 传教士和食人族问题

from typing import List

from com.sweetmanor.algorithm.search.graph_search import bfs, node_to_path

MAX_NUM = 3


class MCState:

    def __init__(self, missionaries: int, cannibals: int, boat: bool) -> None:
        self.wm = missionaries  # 西岸传教士
        self.wc = cannibals  # 西岸食人族
        self.em = MAX_NUM - self.wm  # 东岸传教士
        self.ec = MAX_NUM - self.wc  # 东岸食人族
        self.boat = boat  # 西岸是否有船

    def goal_test(self) -> bool:
        """ 测试是否完成题解 """
        return self.is_legal and self.em == MAX_NUM and self.ec == MAX_NUM

    @property
    def is_legal(self) -> bool:
        """ 当前状态是否符合题目要求 """

        # 西岸 传教士 小于 食人族
        if 0 < self.wm < self.wc:
            return False

        # 东岸 传教士 小于 食人族
        if 0 < self.em < self.ec:
            return False

        return True

    def successors(self) -> List['MCState']:
        """ 查找当前状态可进行的操作

            尝试添加所有可能的组合，添加完后滤掉不符合要求的步骤。
        """

        sucs: List[MCState] = []
        if self.boat:  # 西岸有船
            # 坐2个传教士
            if self.wm > 1:
                sucs.append(MCState(self.wm - 2, self.wc, not self.boat))

            # 坐1个传教士
            if self.wm > 0:
                sucs.append(MCState(self.wm - 1, self.wc, not self.boat))

            # 坐2个食人族
            if self.wc > 1:
                sucs.append(MCState(self.wm, self.wc - 2, not self.boat))

            # 坐1个食人族
            if self.wc > 0:
                sucs.append(MCState(self.wm, self.wc - 1, not self.boat))

            # 坐1个传教士和1个食人族
            if self.wm > 0 and self.wc > 0:
                sucs.append(MCState(self.wm - 1, self.wc - 1, not self.boat))
        else:  # 东岸有船
            # 坐2个传教士
            if self.em > 1:
                sucs.append(MCState(self.wm + 2, self.wc, not self.boat))

            # 坐1个传教士
            if self.em > 0:
                sucs.append(MCState(self.wm + 1, self.wc, not self.boat))

            # 坐2个食人族
            if self.ec > 1:
                sucs.append(MCState(self.wm, self.wc + 2, not self.boat))

            # 坐1个食人族
            if self.ec > 0:
                sucs.append(MCState(self.wm, self.wc + 1, not self.boat))

            # 坐1个传教士和1个食人族
            if self.em > 0 and self.ec > 0:
                sucs.append(MCState(self.wm + 1, self.wc + 1, not self.boat))

        # 移除不符合要求的移动
        return [x for x in sucs if x.is_legal]

    def __str__(self):
        return ("西岸传教士={}, 西岸食人族={}, 东岸传教士={}, 东岸食人族={}, {}有船"
                .format(self.wm, self.wc, self.em, self.ec, "西岸" if self.boat else "东岸"))


def display_solution(path: List[MCState]):
    """ 打印题解步骤 """
    if len(path) == 0:
        return

    old_state = path[0]

    setp = 0  # 步骤计数器
    print(setp, end='、 ')
    setp += 1
    print(old_state)

    for current_state in path[1:]:
        if current_state.boat:
            print(
                f"    {old_state.em - current_state.em} 传教士 和 {old_state.ec - current_state.ec} 食人族 从 东岸 移动到 西岸")
        else:
            print(
                f"    {old_state.wm - current_state.wm} 传教士 和 {old_state.wc - current_state.wc} 食人族 从 西岸 移动到 东岸")

        print(setp, end='、 ')
        setp += 1
        print(current_state)
        old_state = current_state


if __name__ == '__main__':
    start = MCState(MAX_NUM, MAX_NUM, True)

    solution = bfs(start, MCState.goal_test, MCState.successors)
    if solution is None:
        print("没有找到题解!")
    else:
        path = node_to_path(solution)
        display_solution(path)
