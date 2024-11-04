# 《算法精粹》
# 通用查找示例

from typing import TypeVar, Iterable, Protocol, Any, Sequence

T = TypeVar('T')
C = TypeVar('C', bound='Comparable')


class Comparable(Protocol):

    def __eq__(self, other: Any) -> bool:
        pass

    def __lt__(self: C, other: C) -> bool:
        pass

    def __gt__(self: C, other: C) -> bool:
        return (not self < other) and self != other

    def __le__(self: C, other: C) -> bool:
        return self < other or self == other

    def __ge__(self: C, other: C) -> bool:
        return not self < other


def linear_contains(iter: Iterable, key: T) -> bool:
    for item in iter:
        if item == key:
            return True

    return False


def binary_contains(sequence: Sequence[C], key: C) -> bool:
    low: int = 0
    high: int = len(sequence) - 1

    while low <= high:
        mid: int = (low + high) // 2
        if sequence[mid] < key:
            low = mid + 1
        elif sequence[mid] > key:
            high = mid - 1
        else:
            return True

    return False
