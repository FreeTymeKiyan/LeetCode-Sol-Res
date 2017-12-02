// 172. Factorial Trailing Zeroes
/**
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Note: Your solution should be in logarithmic time complexity.
 *
 * Tags: Math
 *
 * Similar Problems: (H) Number of Digit One
 */


#include "stdafx.h"

// in the factorial, there are many 2s, so it is enough just to count the number of 5
class Solution {
public:
    int trailingZeroes(int n) {
        int count = 0;
        while (n > 0)
        {
            n /= 5;
            count += n;
        }

        return count;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

