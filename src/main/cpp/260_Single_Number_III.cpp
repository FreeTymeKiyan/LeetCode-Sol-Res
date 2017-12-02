//260 Single Number III
/*
 *Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 *
 *For example:
 *
 *Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *
 *Note:
 *The order of the result is not important. So in the above example, [5, 3] is also correct.
 *Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 *Tag: Bit Manipulation
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <vector>

using namespace std;

class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        int xor_1 = 0;
        for (int i = 0; i < nums.size(); i++) // find the xor result of all the numbers
        {
            xor_1 = xor_1 ^ nums[i];
        }

        int first_diff_index = 0;
        for (first_diff_index = 0; first_diff_index < 32; first_diff_index++) // find the first differten bit between the two single numbers
        {
            if ((xor_1 >> first_diff_index) & 1 == 1)
            {
                break;
            }
        }

        int xor_2 = 0;
        for (int j = 0; j < nums.size(); j++) // find one of the two single numbers
        {
            int temp = (nums[j] >> first_diff_index);
            if (temp & 1 == 1)
            {
                xor_2 = xor_2 ^ nums[j];
            }
        }

        vector<int> result;
        result.push_back(xor_2);
        result.push_back(xor_1 ^ xor_2); //Another number can be calulated as xor ^ xor_2 because a ^ b = c => a = b ^ c and b = a ^ c

        return result;
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> input;
    input.push_back(1);
	input.push_back(2);
    input.push_back(1);
    input.push_back(3);
    input.push_back(5);
    input.push_back(2);

    Solution _sol;
    _sol.singleNumber(input);
    
    return 0;
}

