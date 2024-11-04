# Python解释器加载模块的查找路径


import sys

if __name__ == '__main__':
    # 模块的查找路径存放在sys.path列表中
    for p in sys.path:
        print(p)
