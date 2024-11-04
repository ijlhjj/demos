# Python支持的语言和地区代码


import locale


def print_locales():
    # 格式化打印所有语言和地区代码
    for key, value in locale.locale_alias.items():
        print(f'{key:32} {value}')


if __name__ == '__main__':
    print_locales()
