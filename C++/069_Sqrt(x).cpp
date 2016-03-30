// 069. Sqrt(x)
/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 *
 * Tags: Math, Binary Search
 *
 * Similar Problems: (M) Pow(x, n)
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>

using namespace std;

// binary search, time: O(logn)
class Solution {
public:
    int mySqrt(int x) {
        if (x < 0)
        {
            return INT_MIN;
        }

        
        if (x < 1)
        {
            return x;
        }
        
        int left = 1, right = x;
        while (left <= right)
        {
            int mid = left + (right - left) / 2;    // avoid overflow
            int factor = x / mid;

            if (mid == factor)
            {
                return factor;
            }
            else if (mid < factor)
            {
                left = mid + 1;
            }
            else // mid > factor
            {
                right = mid - 1;
            }
        }

        return right;
    }
};

// bit manipulation, time: O(logn)
class Solution_Bit {
public:
    int mySqrt(int x) {
        if (x < 0)
        {
            return INT_MIN;
        }

        int res = 0;
        // the max int is 2^32 - 1, so the max square root is 2^16
        // we can use a 16-bit mask, set each bit and test
        for (int mask = 1 << 15; mask != 0; mask >>= 1)
        {
            int num = res | mask;
            if (num <= x / num)    // if the square of num is smaller than x, keep current bit
            {
                res = num;
            }
        }

        return res;
    }
};

// newton's method
class Solution_Newton {
public:
    int mySqrt(int x) {
        if (x < 0)
        {
            return INT_MIN;
        }

        double root = 0, newroot = 1;
        // newroot = root - (root^2 - x) / (2 * root)
        //         = (root + x / root) / 2
        while (abs(newroot - root) > 0.001)
        {
            root = newroot;
            newroot = (root + x / root) / 2;
        }

        return static_cast<int>(newroot);
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    int x = 8;
    Solution_Newton mySolution;
    int res = mySolution.mySqrt(x);
    cout << res << endl;
    system("pause");
    return 0;
}

