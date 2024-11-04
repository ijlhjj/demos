#include <stdio.h>
#include <math.h>

void narcissisticNumber1()
{
    for (int i = 100; i < 1000; i++)
    {
        int a = i % 10;      // 个位
        int b = i / 10 % 10; // 十位
        int c = i / 100;     // 百位

        // 符合水仙花数定义，打印输出
        if (pow(a, 3) + pow(b, 3) + pow(c, 3) == i)
            printf("%6d", i);
    }
    printf("\n");
}

void narcissisticNumber2()
{
    for (int a = 1; a <= 9; a++)
    { // 百位
        for (int b = 0; b <= 9; b++)
        { // 十位
            for (int c = 0; c <= 9; c++)
            { // 个位
                int n = a * 100 + b * 10 + c;

                // 符合水仙花数定义，打印输出
                if (pow(a, 3) + pow(b, 3) + pow(c, 3) == n)
                    printf("%6d", n);
            }
        }
    }
    printf("\n");
}

int main(int argc, char const *argv[])
{
    narcissisticNumber1();
    narcissisticNumber2();
}
