// 15. 3Sum.cpp
/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * 
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ¡Ü b ¡Ü c)
 * The solution set must not contain duplicate triplets.
 *     For example, given array S = {-1 0 1 2 -1 -4},
 * 
 *     A solution set is:
 *     (-1, 0, 1)
 *     (-1, -1, 2)
 * 
 * Tags: Array, Two Pointers
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

class Solution {
public:
    vector<vector<int>> threeSum(vector<int>& nums) {
        vector<vector<int>> result;
        int n = nums.size();
        if (n < 3)
        {
            return result;
        }

        // sort the array
        sort(nums.begin(), nums.end());

        for (int i = 0; i < n - 2; i++)
        {
            // use two pointers, search if nums[left] + nums[right] == -nums[i]
            int left = i + 1, right = n - 1;

            // stop at positive integer
            if (nums[i] > 0)
            {
                break;
            }

            // skip duplicates
            if ((i > 0) && (nums[i] == nums[i - 1]))
            {
                continue;
            }

            while (left < right)
            {
                // stop if the sum of two smaller numbers is already bigger than zero
                if (nums[i] + nums[left] > 0)
                {
                    break;
                }

                int sum = nums[left] + nums[right] + nums[i];

                if (sum < 0)
                {
                    left++;
                    while ((left < right) && (nums[left] == nums[left - 1]))    // skip duplicates from left
                    {
                        left++;
                    }
                }
                else if (sum > 0)
                {
                    right--;
                    while ((left < right) && (nums[right] == nums[right + 1]))    // skip duplicates from right
                    {
                        right--;
                    }
                }
                else // sum == 0
                {
                    vector<int> triplets;
                    triplets.push_back(nums[i]);
                    triplets.push_back(nums[left]);
                    triplets.push_back(nums[right]);
                    result.push_back(triplets);

                    left++;
                    while ((left < right) && (nums[left] == nums[left - 1]))    // skip duplicates from left
                    {
                        left++;
                    }

                    right--;
                    while ((left < right) && (nums[right] == nums[right + 1]))    // skip duplicates from right
                    {
                        right--;
                    }
                }
            }
        }

        return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;

    // nums = {-1, 0, 1, 2, -1, -4}
    nums.push_back(-1);
    nums.push_back(0);
    nums.push_back(1);
    nums.push_back(2);
    nums.push_back(-1);
    nums.push_back(-4);

    Solution mySolution;
    vector<vector<int>> result = mySolution.threeSum(nums);

    for (int i = 0; i < result.size(); i++)
    {
        for (int j = 0; j < result[i].size(); j++)
        {
            cout << result[i][j] << " ";
        }
        cout << endl;
    }
    system("pause");

    return 0;
}
