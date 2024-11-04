# 《算法精粹》
# 图遍历算法

from collections import deque
from queue import PriorityQueue
from typing import TypeVar, Generic, Optional, Callable, List

T = TypeVar('T')
C = TypeVar("C", bound="Comparable")


class Node(Generic[T]):
    def __init__(self, state: T, parent: Optional['Node'], cost: float = 0.0, heuristic: float = 0.0) -> None:
        self.state = state
        self.parent = parent
        self.cost = cost
        self.heuristic = heuristic

    def __lt__(self, other: 'Node') -> bool:
        return (self.cost + self.heuristic) < (other.cost + other.heuristic)


def dfs(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]]) -> Optional[Node[T]]:
    """ 深度优先搜索

    :param initial: 起点
    :param goal_test: 测试是否到达终点函数
    :param successors: 查找可移动位置函数
    :return: 找到路径返回终点，否则返回 None
    """

    # 使用栈记录当前遍历路径
    frontier: deque[Node[T]] = deque()
    frontier.append(Node(initial, None))

    # 使用集合记录已遍历节点
    explored = {initial}

    # 栈不为空继续遍历
    while len(frontier) > 0:
        current_node = frontier.pop()
        current_state = current_node.state

        # 到达终点
        if goal_test(current_state):
            return current_node

        # 深度优先遍历
        for child in successors(current_state):
            if child not in explored:  # 未访问节点
                explored.add(child)
                frontier.append(Node(child, current_node))

    return None  # 未找到路径


def bfs(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]]) -> Optional[Node[T]]:
    """ 广度优先搜索

    :param initial: 起点
    :param goal_test: 测试是否到达终点函数
    :param successors: 查找可移动位置函数
    :return: 找到路径返回终点，否则返回 None
    """

    # 使用队列记录当前遍历路径
    frontier: deque[Node[T]] = deque()
    frontier.append(Node(initial, None))

    # 使用集合记录已遍历节点
    explored = {initial}

    while len(frontier) > 0:
        current_node = frontier.popleft()
        current_state = current_node.state

        # 到达终点
        if goal_test(current_state):
            return current_node

        # 广度优先遍历
        for child in successors(current_state):
            if child not in explored:  # 未访问节点
                explored.add(child)
                frontier.append(Node(child, current_node))

    return None  # 未找到路径


def astar(initial: T, goal_test: Callable[[T], bool], successors: Callable[[T], List[T]],
          heuristic: Callable[[T], float]) -> Optional[Node[T]]:
    """ A* 搜索

    :param initial: 起点
    :param goal_test: 测试是否到达终点函数
    :param successors: 查找可移动位置函数
    :param heuristic: 启发式函数
    :return: 找到路径返回终点，否则返回 None
    """

    # 使用优先队列记录当前遍历路径
    frontier: PriorityQueue[Node[T]] = PriorityQueue()
    frontier.put(Node(initial, None, 0.0, heuristic(initial)))

    # 使用映射记录已遍历节点
    explored = {initial: 0.0}

    while not frontier.empty():
        current_node = frontier.get()
        current_state = current_node.state

        # 到达终点
        if goal_test(current_state):
            return current_node

        for child in successors(current_state):
            new_cost = current_node.cost + 1
            # 未访问节点或找到更低成本
            if child not in explored or explored[child] > new_cost:
                explored[child] = new_cost
                frontier.put(Node(child, current_node, new_cost, heuristic(child)))

    return None  # 未找到路径


def node_to_path(node: Node[T]) -> List[T]:
    """ 生成成功遍历路径 """

    path = [node.state]

    while node.parent is not None:
        node = node.parent
        path.append(node.state)

    path.reverse()  # 反序

    return path
