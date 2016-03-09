//136 Single Number
/*
 *Given an array of integers, every element appears twice except for one. Find that single one.
 *
 *Note:
 *Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 *
 *Tag: Hash Table, Bit Manipulation
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <vector>
#include <algorithm>

using namespace std;

class Solution1 { // use sort 
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
				if (cnt < 2)
				{
					return nums[i];
				}
				cnt = 1;
			}
		}

		return nums[n-1];
    }
};

class Solution2 { // use XOR
public:
    int singleNumber(vector<int>& nums) {
		int n = nums.size();
		if (n == 0)
		{
			return -1;
		}

        int result = nums[0];
        for (int i = 1; i < nums.size(); i++)
        {
            result = result ^ nums[i];
        }

        return result;
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}

