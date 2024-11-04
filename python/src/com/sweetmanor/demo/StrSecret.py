# 《算法精粹》
# 文本序列加解密 （牢不可破的加密方案）

from secrets import token_bytes
from typing import Tuple


def random_key(length: int) -> int:
    tb: bytes = token_bytes(length)  # 生成随机数据
    return int.from_bytes(tb)  # 默认为 "big"


def encrypt(original: str) -> Tuple[int, int]:
    original_bytes: bytes = original.encode()

    dummy: int = random_key(len(original_bytes))  # 生成随机假数据

    original_key: int = int.from_bytes(original_bytes)  # 默认为 "big"

    encrypted: int = original_key ^ dummy  # XOR

    return dummy, encrypted


def decrypt(key1: int, key2: int) -> str:
    decrypted: int = key1 ^ key2  # XOR

    # int.to_bytes() 将 int 转换为 bytes
    # 字节数 = 总位长 除以 8（每字节的位数）
    # 整除操作（//）之前，必须给解密数据的长度加上 7，以确保能“向上舍入”，避免出现边界差一（off-by-one）错误
    temp: bytes = decrypted.to_bytes((decrypted.bit_length() + 7) // 8)  # 默认为 "big"

    # bytes 类型的 decode() 方法返回一个 str
    return temp.decode()


if __name__ == "__main__":
    key1, key2 = encrypt("One Time Pad!")
    result: str = decrypt(key1, key2)
    print(result)
