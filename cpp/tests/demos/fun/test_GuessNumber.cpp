#include "gtest/gtest.h"

#include "demos.h"

TEST(GuessNumber, test1)
{
    vector<vector<int>> guesses = {
        {4815, 2, 1},
        {5716, 1, 0},
        {7842, 1, 0},
        {4901, 0, 0},
        {8585, 3, 3},
        {8555, 3, 2}};

    ASSERT_EQ(3585, guessNumber(guesses));
}

TEST(GuessNumber, test2)
{
    vector<vector<int>> guesses = {
        {4815, 0, 0},
        {2999, 3, 3}};

    ASSERT_EQ(-1, guessNumber(guesses));
}
