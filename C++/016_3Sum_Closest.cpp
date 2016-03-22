// 016. 3Sum Closest
/**
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * 
 *     For example, given array S = {-1 2 1 -4}, and target = 1.
 * 
 *     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * 
 * Tags: Array, Two Pointers
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <algorithm>
#include <vector>

using namespace std;

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int n = nums.size();
        if (n < 3)
        {
            return 0;
        }

        // sort the array
        sort(nums.begin(), nums.end());

        // let the result be the possible maximum
        int result = nums[n - 1] + nums[n - 2] + nums[n - 3];
        if (result <= target)
        {
            return result;
        }

        // let the result be the possible minimum
        result = nums[0] + nums[1] + nums[2];
        if (result >= target)
        {
            return result;
        }

        for (int i = 0; i < n - 2; i++)
        {
            // use two pointers, adjust sum by moving left or right pointer
            int left = i + 1, right = n - 1;

            while (left < right)
            {
                int sum = nums[left] + nums[right] + nums[i];

                if (abs(sum - target) < abs(result - target))
                {
                    result = sum;
                }

                if (sum < target)
                {
                    left++;    // make sum larger
                    while ((left < right) && (nums[left] == nums[left - 1]))    // skip duplicates from left
                    {
                        left++;
                    }

                }
                else if (sum > target)
                {
                    right--;    // make sum smaller
                    while ((left < right) && (nums[right] == nums[right + 1]))    // skip duplicates from right
                    {
                        right--;
                    }
                }
                else    // sum == target
                {
                    return sum;
                }
            }
        }

        return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;
    int target = 1;

    // nums = {-1, 2, 1, -4}
    nums.push_back(-1);
    nums.push_back(2);
    nums.push_back(1);
    nums.push_back(-4);

    Solution mySolution;
    int result = mySolution.threeSumClosest(nums, target);

    return 0;
}
