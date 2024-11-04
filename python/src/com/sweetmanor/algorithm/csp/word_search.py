# 《算法精粹》
# 单词字谜游戏

from random import choice
from string import ascii_uppercase
from typing import List, NamedTuple, Dict, Optional

from csp import Constraint, CSP

# type alias for grids
Grid = List[List[str]]


class GridLocation(NamedTuple):
    row: int
    column: int


class WordSearchConstraint(Constraint[str, List[GridLocation]]):
    """ 网格约束 """

    def __init__(self, words: List[str]) -> None:
        super().__init__(words)
        self.words = words

    def satisfied(self, assignment: Dict[str, List[GridLocation]]) -> bool:
        # assignment 展平为一个列表
        all_locations = [locs for values in assignment.values() for locs in values]
        # 占用网格不存在重复
        return len(set(all_locations)) == len(all_locations)


def generate_grid(rows: int, columns: int) -> Grid:
    """ 随机填充网格字符 """
    return [[choice(ascii_uppercase) for c in range(columns)] for r in range(rows)]


def display_grid(grid: Grid) -> None:
    """ 打印网格 """
    for row in grid:
        print("".join(row))


def generate_domain(word: str, grid: Grid) -> List[List[GridLocation]]:
    """ 生成单词值域 """

    domain: List[List[GridLocation]] = []
    height = len(grid)  # 行数
    width = len(grid[0])  # 列数
    length = len(word)

    # 遍历网格，查找所有可以放置单词的值域列表
    # 值域的搜索一共8个方向，添加4个方向即可，另4个方向与添加的4个反向
    for row in range(height):
        for col in range(width):
            columns = range(col, col + length)
            rows = range(row, row + length)

            if col + length <= width:
                # 从左向右的值域
                domain.append([GridLocation(row, c) for c in columns])
                if row + length <= height:
                    # 向右下的值域
                    domain.append([GridLocation(r, col + (r - row)) for r in rows])
            if row + length <= height:
                # 从上向下的值域
                domain.append([GridLocation(r, col) for r in rows])
                if col + 1 - length >= 0:
                    # 向左下的值域
                    domain.append([GridLocation(r, col - (r - row)) for r in rows])

    return domain


if __name__ == "__main__":
    grid = generate_grid(9, 9)

    # 变量为 5 个单词
    words = ["MATTHEW", "JOE", "MARY", "SARAH", "SALLY"]

    # 为每个单词生成值域
    locations: Dict[str, List[List[GridLocation]]] = {}
    for word in words:
        locations[word] = generate_domain(word, grid)

    csp = CSP(words, locations)  # 创建解题框架

    # 添加约束
    csp.add_constraint(WordSearchConstraint(words))

    solution: Optional[Dict[str, List[GridLocation]]] = csp.backtracking_search()
    if solution is None:
        print("没有找到题解！")
    else:
        for word, grid_locations in solution.items():
            # 随机逆序一些单词位置
            if choice([True, False]):
                grid_locations.reverse()

            # 填充单词
            for index, letter in enumerate(word):
                (row, col) = (grid_locations[index].row, grid_locations[index].column)
                grid[row][col] = letter

        display_grid(grid)  # 打印题解
