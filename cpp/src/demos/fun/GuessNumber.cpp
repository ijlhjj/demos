/**
 * 猜数字：计算机随机产生一个四位数，让玩家猜这个四位数是什么，每猜一个数，计算机都会告诉玩家猜对了几个数，其中有几个数在正确的位置上。
 *      输入的每一行代表一次问答，包含3个整数A、B、C 。玩家猜这个四位数为A，然后计算机回答猜对了B个数字，其中C个在正确的位置上。
 *      如果根据这段对话能确定这个四位数，则返回这个四位数，若不能，则返回 -1 。
 *
 * 使用穷举法，检查符合所有问答的四位数，如果最后能确认只有1个四位数符合，则能确定，否则不能确定。
 */

#include "demos.h"

/**
 * 从数组删除第一个匹配的值
 */
void removeFirst(vector<int> &arr, int val)
{
    // 使用std::find找到值的迭代器
    auto it = find(arr.begin(), arr.end(), val);

    // 检查值是否存在
    if (it != arr.end())
    {
        // 删除第一个匹配的值
        arr.erase(it);
    }
}

int guessNumber(vector<vector<int>> &guesses)
{
    // 初始所有4位数列表
    vector<int> nums;
    for (int i = 1000; i < 10000; i++)
        nums.push_back(i);

    for (auto guess : guesses)
    { // 遍历问答列表
        vector<int> accordNums;

        // 拆分所猜4位数的各数位值
        int x = guess[0];
        int A1 = x / 1000;       // 千位
        int B1 = x % 1000 / 100; // 百位
        int C1 = x % 100 / 10;   // 十位
        int D1 = x % 10;         // 个位

        vector<int> digits = {A1, B1, C1, D1}; // 位值列表

        // 遍历剩余列表以检查符合本次问答条件的值
        for (auto n : nums)
        {
            // 拆分当前数的各个位
            int A2 = n / 1000;
            int B2 = n % 1000 / 100;
            int C2 = n % 100 / 10;
            int D2 = n % 10;

            // 创建列表并删除猜对的数位
            vector<int> remainDigits = {digits};
            removeFirst(remainDigits, A2);
            removeFirst(remainDigits, B2);
            removeFirst(remainDigits, C2);
            removeFirst(remainDigits, D2);

            // 统计在正确位置上的数量
            int cnt = 0;
            if (A1 == A2)
                cnt++;
            if (B1 == B2)
                cnt++;
            if (C1 == C2)
                cnt++;
            if (D1 == D2)
                cnt++;

            // 两项检查都符合的数字保留到列表中
            if (4 - remainDigits.size() == guess[1] && cnt == guess[2])
                accordNums.push_back(n);
        }

        // 更新剩余列表
        nums = accordNums;
    }

    // 只剩唯一值，返回
    if (nums.size() == 1)
        return nums[0];

    return -1; // 否则返回 -1
}
