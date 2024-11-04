# 《算法精粹》

from dataclasses import dataclass


@dataclass
class Edge:
    u: int  # 起点
    v: int  # 终点

    def reversed(self) -> 'Edge':
        return Edge(self.v, self.u)

    def __str__(self):
        return f'{self.u} -> {self.v}'
