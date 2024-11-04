/**
 * 百鸡百钱：公鸡一个五块钱，母鸡一个三块钱，小鸡三个一块钱，现在要用一百块钱买一百只鸡，问公鸡、母鸡、小鸡各多少只？
 *      3x+5y+z/3=100
 *      x+y+z=100
 */

#include <stdio.h>

/**
 * 3层for循环，时间复杂度：O(n^3)
 */
void hundredChickens1()
{
    for (int x = 0; x <= 100; x++)
    {
        for (int y = 0; y <= 100; y++)
        {
            for (int z = 0; z <= 100; z += 3)
            {
                if (x + y + z == 100 && 3 * x + 5 * y + z / 3 == 100)
                    printf("%d %d %d\n", x, y, z);
            }
        }
    }
}

/**
 * 消减未知数，减少循环次数。时间复杂度：O(n)
 * 4x+7y=100
 */
void hundredChickens2()
{
    for (int x = 0; x <= 25; x++)
    { // x最大为25
        int y = 100 - 4 * x;
        if (y >= 0 && y % 7 == 0)
        {
            y /= 7;
            int z = 100 - x - y;
            if (z % 3 == 0 && 3 * x + 5 * y + z / 3 == 100)
                printf("%d %d %d\n", x, y, z);
        }
    }
}

int main(int argc, char const *argv[])
{
    hundredChickens1();
    hundredChickens2();
}
