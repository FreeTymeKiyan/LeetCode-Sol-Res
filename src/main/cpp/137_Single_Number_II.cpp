//137 Single Number II
/*
 *Given an array of integers, every element appears three times except for one. Find that single one.
 *
 *Note:
 *Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 *Tag: Bit Manipulation
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <vector>
#include <algorithm>

using namespace std;

class Solution1 { //use sort
public:
    int singleNumber(vector<int>& nums) {
        int n = nums.size();
        if (n == 0)
        {
            return -1;
        }

        if (n == 1)
        {
            return nums[0];
        }

        sort(nums.begin(), nums.end());
        int cnt = 1;

        for (int i = 0; i < n - 1; i++)
        {
            if (nums[i] == nums[i+1])
            {
                cnt++;
            }
            else
            {
                if (cnt < 3)
                {
                    return nums[i];
                }
                cnt = 1;
            }
        }

        return nums[n-1];
    }
};

class Solution2 { // use bit manipulation
public:
    int singleNumber(vector<int>& nums) {
        vector<int> bit(32,0);

        for(int i = 0; i < nums.size(); i++)
        {
            for(int j =0; j<32; ++j)
            {
                int shiftNumber = nums[i]>>j;
                if(shiftNumber == 0) break;
                bit[j] += shiftNumber & 1;
            }
        }

        int result = 0;
        for(int i = 0; i < 32; i++)
        {
            result += (bit[i]%3 << i);
        }
        return result;
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

