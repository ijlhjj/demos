# 《算法精粹》
# 迷宫求解
# 迷宫是随机生成的，所以不是每个迷宫都会有解

import random
from enum import Enum
from math import sqrt
from typing import NamedTuple, List, Callable

from com.sweetmanor.algorithm.search.graph_search import dfs, node_to_path, bfs, astar


class Cell(str, Enum):
    EMPTY = " "
    BLOCKED = "X"
    START = "S"
    GOAL = "G"
    PATH = "*"


class MazeLocation(NamedTuple):
    row: int
    column: int


def euclidean_distance(goal: MazeLocation) -> Callable[[MazeLocation], float]:
    """ 欧氏距离 """

    def distance(ml: MazeLocation) -> float:
        xdist = ml.column - goal.column
        ydist = ml.row - goal.row
        return sqrt((xdist * xdist) + (ydist * ydist))

    return distance


def manhattan_distance(goal: MazeLocation) -> Callable[[MazeLocation], float]:
    """ 曼哈顿距离 """

    def distance(ml: MazeLocation) -> float:
        xdist = abs(ml.column - goal.column)
        ydist = abs(ml.row - goal.row)
        return xdist + ydist

    return distance


class Maze:

    def __init__(self, rows: int = 10, columns: int = 10, sparseness: float = 0.2,
                 start: MazeLocation = MazeLocation(0, 0), goal: MazeLocation = MazeLocation(9, 9)) -> None:
        self._rows = rows
        self._columns = columns
        self.start = start
        self.goal = goal

        # 初始化迷宫网格
        self._grid = [[Cell.EMPTY for c in range(columns)] for r in range(rows)]

        # 随机添加障碍
        self._randomly_fill(rows, columns, sparseness)

        # 标记起点和终点
        self._mark_start_goal()

    def _randomly_fill(self, rows: int, columns: int, sparseness: float):
        """ 随机填充障碍 """
        for row in range(rows):
            for col in range(columns):
                if random.uniform(0, 1.0) < sparseness:
                    self._grid[row][col] = Cell.BLOCKED

    def goal_test(self, ml: MazeLocation) -> bool:
        """ 测试是否到达终点 """
        return ml == self.goal

    def successors(self, ml: MazeLocation) -> List[MazeLocation]:
        """ 查找可移动位置 """

        locations: List[MazeLocation] = []

        if ml.row + 1 < self._rows and self._grid[ml.row + 1][ml.column] != Cell.BLOCKED:
            locations.append(MazeLocation(ml.row + 1, ml.column))

        if ml.row - 1 >= 0 and self._grid[ml.row - 1][ml.column] != Cell.BLOCKED:
            locations.append(MazeLocation(ml.row - 1, ml.column))

        if ml.column + 1 < self._columns and self._grid[ml.row][ml.column + 1] != Cell.BLOCKED:
            locations.append(MazeLocation(ml.row, ml.column + 1))

        if ml.column - 1 >= 0 and self._grid[ml.row][ml.column - 1] != Cell.BLOCKED:
            locations.append(MazeLocation(ml.row, ml.column - 1))

        return locations

    def mark_assign_cell(self, path: List[MazeLocation], cell: Cell):
        """ 标注指定网格类型 """
        for maze_location in path:
            self._grid[maze_location.row][maze_location.column] = cell
        self._mark_start_goal()

    def _mark_start_goal(self):
        """ 标记起点和终点 """
        self._grid[self.start.row][self.start.column] = Cell.START
        self._grid[self.goal.row][self.goal.column] = Cell.GOAL

    def __str__(self) -> str:
        output = ""
        for row in self._grid:
            output += " ".join([c.value for c in row]) + "\n"
        return output


if __name__ == '__main__':
    maze = Maze()
    print("生成随机迷宫：")
    print(maze)

    print("深度优先遍历解法：")
    solution1 = dfs(maze.start, maze.goal_test, maze.successors)
    if solution1 is None:
        print("No solution found using depth-first search!")
    else:
        path1 = node_to_path(solution1)
        maze.mark_assign_cell(path1, Cell.PATH)
        print(maze)
        maze.mark_assign_cell(path1, Cell.EMPTY)

    print("广度优先遍历解法：")
    solution2 = bfs(maze.start, maze.goal_test, maze.successors)
    if solution2 is None:
        print("No solution found using breadth-first search!")
    else:
        path2 = node_to_path(solution2)
        maze.mark_assign_cell(path2, Cell.PATH)
        print(maze)
        maze.mark_assign_cell(path2, Cell.EMPTY)

    print("A* 遍历解法：")
    distance = manhattan_distance(maze.goal)
    solution3 = astar(maze.start, maze.goal_test, maze.successors, distance)
    if solution3 is None:
        print("No solution found using A*!")
    else:
        path3 = node_to_path(solution3)
        maze.mark_assign_cell(path3, Cell.PATH)
        print(maze)
