# 《算法精粹》
# 图

from typing import TypeVar, Generic, List, Optional

from com.sweetmanor.algorithm.graph.edge import Edge
from com.sweetmanor.algorithm.search.graph_search import bfs, Node, node_to_path

V = TypeVar('V')


class Graph(Generic[V]):

    def __init__(self, vertices: List[V] = []) -> None:
        # 顶点列表的下标和边列表的下标一一对应，即顶点下标 i 的边的下标也是 i
        self._vertices = vertices
        self._edges: List[List[Edge]] = [[] for _ in vertices]

    @property
    def vertex_count(self) -> int:
        return len(self._vertices)

    @property
    def edge_count(self) -> int:
        return sum(map(len, self._edges))

    def add_vertex(self, vertex: V) -> int:
        self._vertices.append(vertex)
        self._edges.append([])
        return self.vertex_count - 1

    def add_edge(self, edge: Edge) -> None:
        self._edges[edge.u].append(edge)
        self._edges[edge.v].append(edge.reversed())

    def add_edge_by_indices(self, u: int, v: int) -> None:
        edge = Edge(u, v)
        self.add_edge(edge)

    def add_edge_by_vertices(self, first: V, second: V) -> None:
        u = self._vertices.index(first)
        v = self._vertices.index(second)
        self.add_edge_by_indices(u, v)

    def vertex_at(self, index: int) -> V:
        return self._vertices[index]

    def index_of(self, vertex: V) -> int:
        return self._vertices.index(vertex)

    def neighbors_for_index(self, index: int) -> List[V]:
        return list(map(self.vertex_at, [e.v for e in self._edges[index]]))

    def neighbors_for_vertex(self, vertex: V) -> List[V]:
        return self.neighbors_for_index(self.index_of(vertex))

    def edges_for_index(self, index: int) -> List[Edge]:
        return self._edges[index]

    def edges_for_vertex(self, vertex: V) -> List[Edge]:
        return self.edges_for_index(self.index_of(vertex))

    def __str__(self):
        desc = ""
        for i in range(self.vertex_count):
            desc += f"{self.vertex_at(i)} -> {self.neighbors_for_index(i)}\n"
        return desc


if __name__ == "__main__":
    city_graph: Graph[str] = Graph(["Seattle", "San Francisco", "Los Angeles", "Riverside", "Phoenix",
                                    "Chicago", "Boston", "New York", "Atlanta", "Miami",
                                    "Dallas", "Houston", "Detroit", "Philadelphia", "Washington"])

    city_graph.add_edge_by_vertices("Seattle", "Chicago")
    city_graph.add_edge_by_vertices("Seattle", "San Francisco")
    city_graph.add_edge_by_vertices("San Francisco", "Riverside")
    city_graph.add_edge_by_vertices("San Francisco", "Los Angeles")
    city_graph.add_edge_by_vertices("Los Angeles", "Riverside")

    city_graph.add_edge_by_vertices("Los Angeles", "Phoenix")
    city_graph.add_edge_by_vertices("Riverside", "Phoenix")
    city_graph.add_edge_by_vertices("Riverside", "Chicago")
    city_graph.add_edge_by_vertices("Phoenix", "Dallas")
    city_graph.add_edge_by_vertices("Phoenix", "Houston")

    city_graph.add_edge_by_vertices("Dallas", "Chicago")
    city_graph.add_edge_by_vertices("Dallas", "Atlanta")
    city_graph.add_edge_by_vertices("Dallas", "Houston")
    city_graph.add_edge_by_vertices("Houston", "Atlanta")
    city_graph.add_edge_by_vertices("Houston", "Miami")

    city_graph.add_edge_by_vertices("Atlanta", "Chicago")
    city_graph.add_edge_by_vertices("Atlanta", "Washington")
    city_graph.add_edge_by_vertices("Atlanta", "Miami")
    city_graph.add_edge_by_vertices("Miami", "Washington")
    city_graph.add_edge_by_vertices("Chicago", "Detroit")

    city_graph.add_edge_by_vertices("Detroit", "Boston")
    city_graph.add_edge_by_vertices("Detroit", "Washington")
    city_graph.add_edge_by_vertices("Detroit", "New York")
    city_graph.add_edge_by_vertices("Boston", "New York")
    city_graph.add_edge_by_vertices("New York", "Philadelphia")

    city_graph.add_edge_by_vertices("Philadelphia", "Washington")

    print(city_graph)

    bfs_result: Optional[Node[V]] = bfs("Boston", lambda x: x == "Miami", city_graph.neighbors_for_vertex)
    if bfs_result is None:
        print("No solution found using breadth-first search!")
    else:
        path = node_to_path(bfs_result)
        print("Path from Boston to Miami:")
        print(path)
