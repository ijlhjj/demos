# 《算法精粹》
# 基因压缩算法

class CompressedGene:

    def __init__(self, gene: str) -> None:
        self._compress(gene)

    def __str__(self) -> str:
        return self.decompress()

    def _compress(self, gene: str) -> None:
        # 数字不能以 0 开头，此处在开头添加一个 1
        self.bitString: int = 1

        for c in gene.upper():
            self.bitString <<= 2  # 左移2位
            if c == 'A':
                self.bitString |= 0b00
            elif c == 'C':
                self.bitString |= 0b01
            elif c == 'G':
                self.bitString |= 0b10
            elif c == 'T':
                self.bitString |= 0b11
            else:
                raise ValueError("Invalid Nucleotide:{}".format(c))

    def decompress(self) -> str:
        gene: str = ""

        # 逆序取值拼装，舍弃最后1位 1
        for i in range(0, self.bitString.bit_length() - 1, 2):
            bits: int = self.bitString >> i & 0b11  # 每次取2位
            if bits == 0b00:
                gene += 'A'
            elif bits == 0b01:
                gene += 'C'
            elif bits == 0b10:
                gene += 'G'
            elif bits == 0b11:
                gene += 'T'
            else:
                raise ValueError("Invalid bits:{}".format(bits))

        return gene[::-1]  # 反序


if __name__ == '__main__':
    from sys import getsizeof

    original: str = "TAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATATAGGGATTAACCGTTATATATATATAGCCATGGATCGATTATA" * 100
    print("original is {} bytes".format(getsizeof(original)))

    # compress
    compressed: CompressedGene = CompressedGene(original)
    print("compressed is {} bytes".format(getsizeof(compressed.bitString)))
    print(compressed)

    # decompress
    print("original and decompressed are the same: {}".format(original == compressed.decompress()))
