# 《算法精粹》
# 基因序列搜索

from enum import IntEnum
from typing import Tuple, List

Nucleotide: IntEnum = IntEnum('Nucleotide', ('A', 'C', 'G', 'T'))
Codon = Tuple[Nucleotide, Nucleotide, Nucleotide]
Gene = List[Codon]


def string_to_gene(s: str) -> Gene:
    gene: Gene = []

    for i in range(0, len(s) - 3, 3):
        codon: Codon = (Nucleotide[s[i]], Nucleotide[s[i + 1]], Nucleotide[s[i + 2]])
        gene.append(codon)

    return gene


def linear_contains(gene: Gene, key_codon: Codon) -> bool:
    # 线性查找
    for codon in gene:
        if codon == key_codon:
            return True

    return False


def binary_contains(gene: Gene, key_codon: Codon) -> bool:
    # 二分查找
    low: int = 0
    high: int = len(gene) - 1

    while low <= high:
        mid: int = (low + high) // 2
        if gene[mid] < key_codon:
            low = mid + 1
        elif gene[mid] > key_codon:
            high = mid - 1
        else:
            return True

    return False


if __name__ == '__main__':
    gene_str: str = "ACGTGGCTCTCTAACGTACGTACGTACGGGGTTTATATATACCCTAGGACTCCCTTT"
    my_gene: Gene = string_to_gene(gene_str)

    acg: Codon = (Nucleotide.A, Nucleotide.C, Nucleotide.G)
    gat: Codon = (Nucleotide.G, Nucleotide.A, Nucleotide.T)

    print(linear_contains(my_gene, acg))  # True
    print(linear_contains(my_gene, gat))  # False

    my_sorted_gene: Gene = sorted(my_gene)  # 排序
    print(binary_contains(my_sorted_gene, acg))  # True
    print(binary_contains(my_sorted_gene, gat))  # False
