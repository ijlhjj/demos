# 《算法精粹》
# 加权图

from dataclasses import dataclass
from queue import PriorityQueue
from typing import TypeVar, Generic, List, Tuple, Optional, Dict

from graph import Graph
from weighted_edge import WeightedEdge

V = TypeVar('V')
WeightedPath = List[WeightedEdge]


@dataclass
class DijkstraNode:
    vertex: int
    distance: float

    def __lt__(self, other: 'DijkstraNode') -> bool:
        return self.distance < other.distance

    def __eq__(self, other: 'DijkstraNode') -> bool:
        return self.distance == other.distance


class WeightedGraph(Generic[V], Graph[V]):

    def __init__(self, vertices: List[V] = []) -> None:
        self._vertices = vertices
        self._edges: List[List[WeightedEdge]] = [[] for _ in vertices]

    def add_edge_by_indices(self, u: int, v: int, weight: float) -> None:
        edge = WeightedEdge(u, v, weight)
        self.add_edge(edge)

    def add_edge_by_vertices(self, first: V, second: V, weight: float) -> None:
        u = self._vertices.index(first)
        v = self._vertices.index(second)
        self.add_edge_by_indices(u, v, weight)

    def neighbors_for_index_with_weights(self, index: int) -> List[Tuple[V, float]]:
        distance_tuples: List[Tuple[V, float]] = []
        for edge in self.edges_for_index(index):
            distance_tuples.append((self.vertex_at(edge.v), edge.weight))
        return distance_tuples

    def __str__(self):
        desc: str = ""
        for i in range(self.vertex_count):
            desc += f"{self.vertex_at(i)} -> {self.neighbors_for_index_with_weights(i)}\n"
        return desc


def dijkstra(wg: WeightedGraph[V], root: V) -> Tuple[List[Optional[float]], Dict[int, WeightedEdge]]:
    """ Dijkstra 算法 """

    first = wg.index_of(root)  # 获取索引

    # 距离数组，起点为0
    distances: List[Optional[float]] = [None] * wg.vertex_count
    distances[first] = 0

    # 路径映射
    path_dict: Dict[int, WeightedEdge] = {}

    # 优先队列存放待遍历顶点
    pq: PriorityQueue[DijkstraNode] = PriorityQueue()
    pq.put(DijkstraNode(first, 0))

    while not pq.empty():
        u = pq.get().vertex
        dist_u = distances[u]
        # 遍历邻接边
        for we in wg.edges_for_index(u):
            dist_v = distances[we.v]  # 邻接边距离
            # 邻接边未访问或发现更短距离
            if dist_v is None or dist_v > we.weight + dist_u:
                distances[we.v] = we.weight + dist_u  # 更新距离
                path_dict[we.v] = we
                pq.put(DijkstraNode(we.v, we.weight + dist_u))

    return distances, path_dict


def distance_array_to_vertex_dict(wg: WeightedGraph[V], distances: List[Optional[float]]) -> Dict[V, Optional[float]]:
    distance_dict: Dict[V, Optional[float]] = {}
    for i in range(len(distances)):
        distance_dict[wg.vertex_at(i)] = distances[i]
    return distance_dict


def path_dict_to_path(start: int, end: int, path_dict: Dict[int, WeightedEdge]) -> WeightedPath:
    if len(path_dict) == 0:
        return []

    # 逆序添加路径
    edge_path: WeightedPath = []
    e = path_dict[end]
    edge_path.append(e)
    while e.u != start:
        e = path_dict[e.u]
        edge_path.append(e)

    return list(reversed(edge_path))


def mst(wg: WeightedGraph[V], start: int = 0) -> Optional[WeightedPath]:
    """ Prim算法：属于贪心算法 """
    # 校验入参，超出顶点列表范围时返回 None
    if start > wg.vertex_count - 1 or start < 0:
        return None

    path: WeightedPath = []  # 最终路径

    # 使用优先队列查找最小权重的边，使用列表记录已加入连通图顶点（等同于图遍历中的Set）
    pq: PriorityQueue[WeightedEdge] = PriorityQueue()
    visited = [False] * wg.vertex_count

    # 定义内部函数，方便后面多处引用
    def visit(index: int):
        visited[index] = True  # 标记顶点已访问
        for edge in wg.edges_for_index(index):
            # 遍历邻近边，未访问的加入优先队列
            if not visited[edge.v]:
                pq.put(edge)

    visit(start)  # 处理起始顶点
    # 优先队列不为空时继续处理
    while not pq.empty():
        edge = pq.get()
        # 对未访问的邻近边进行处理
        if not visited[edge.v]:
            path.append(edge)
            visit(edge.v)

    return path


def total_weight(wp: WeightedPath) -> float:
    return sum([e.weight for e in wp])


def print_weighted_path(wg: WeightedGraph, wp: WeightedPath) -> None:
    for edge in wp:
        print(f"{wg.vertex_at(edge.u)} {edge.weight} -> {wg.vertex_at(edge.v)}")
    print(f"总计权重：{total_weight(wp)}")


if __name__ == "__main__":
    city_graph = WeightedGraph(["Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix",
                                "Chicago", "Boston", "New York", "Atlanta", "Miami",
                                "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"])

    city_graph.add_edge_by_vertices("Seattle", "Chicago", 1737)
    city_graph.add_edge_by_vertices("Seattle", "San Francisco", 678)
    city_graph.add_edge_by_vertices("San Francisco", "Riverside", 386)
    city_graph.add_edge_by_vertices("San Francisco", "Los Angeles", 348)
    city_graph.add_edge_by_vertices("Los Angeles", "Riverside", 50)

    city_graph.add_edge_by_vertices("Los Angeles", "Phoenix", 357)
    city_graph.add_edge_by_vertices("Riverside", "Phoenix", 307)
    city_graph.add_edge_by_vertices("Riverside", "Chicago", 1704)
    city_graph.add_edge_by_vertices("Phoenix", "Dallas", 887)
    city_graph.add_edge_by_vertices("Phoenix", "Houston", 1015)

    city_graph.add_edge_by_vertices("Dallas", "Chicago", 805)
    city_graph.add_edge_by_vertices("Dallas", "Atlanta", 721)
    city_graph.add_edge_by_vertices("Dallas", "Houston", 225)
    city_graph.add_edge_by_vertices("Houston", "Atlanta", 702)
    city_graph.add_edge_by_vertices("Houston", "Miami", 968)

    city_graph.add_edge_by_vertices("Atlanta", "Chicago", 588)
    city_graph.add_edge_by_vertices("Atlanta", "Washington", 543)
    city_graph.add_edge_by_vertices("Atlanta", "Miami", 604)
    city_graph.add_edge_by_vertices("Miami", "Washington", 923)
    city_graph.add_edge_by_vertices("Chicago", "Detroit", 238)

    city_graph.add_edge_by_vertices("Detroit", "Boston", 613)
    city_graph.add_edge_by_vertices("Detroit", "Washington", 396)
    city_graph.add_edge_by_vertices("Detroit", "New York", 482)
    city_graph.add_edge_by_vertices("Boston", "New York", 190)
    city_graph.add_edge_by_vertices("New York", "Philadelphia", 81)

    city_graph.add_edge_by_vertices("Philadelphia", "Washington", 123)

    print(city_graph)

    path: Optional[WeightedPath] = mst(city_graph)
    if path is None:
        print("No solution found!")
    else:
        print_weighted_path(city_graph, path)

    print()

    distances, path_dict = dijkstra(city_graph, "Los Angeles")
    name_distance: Dict[str, Optional[int]] = distance_array_to_vertex_dict(city_graph, distances)
    print("Distances from Los Angeles:")
    for key, value in name_distance.items():
        print(f"{key} : {value}")

    print()

    print("Shortest path from Los Angeles to Boston:")
    path = path_dict_to_path(city_graph.index_of("Los Angeles"), city_graph.index_of("Boston"), path_dict)
    print_weighted_path(city_graph, path)
