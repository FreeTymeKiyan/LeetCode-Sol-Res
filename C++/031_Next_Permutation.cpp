// 031. Next Permutation
/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 *
 * The replacement must be in-place, do not allocate extra memory.
 *
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 ¡ú 1,3,2
 * 3,2,1 ¡ú 1,2,3
 * 1,1,5 ¡ú 1,5,1
 *
 * Tags: Array
 *
 * Similar Problems: (M) Permutations (M) Permutations II (M) Permutation Sequence (M) Palindrome Permutation II
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <iostream>

using namespace std;

class Solution {
public:
    void nextPermutation(vector<int>& nums) {
        int n = nums.size();
        if (n < 2)
        {
            return;
        }

        // start from the last element, search for the first pair in ascending order
        int i = n - 1;
        while ((i > 0) && (nums[i - 1] >= nums[i]))
        {
            i--;
        }

        // if found, start from the last element, search for the first element that is larger
        if (i != 0)
        {
            int j = n - 1;
            while ((j >= i) && (nums[i - 1] >= nums[j]))
            {
                j--;
            }
            // swap these two elements
            swap(nums[i - 1], nums[j]);
        }

        // reverse the rest of the array
        reverse(nums.begin() + i, nums.end());
        return;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> nums;
    nums.push_back(1);
    nums.push_back(6);
    nums.push_back(6);
    nums.push_back(3);
    cout << "Current Permutation: ";
    for (int i = 0; i < nums.size(); i++)
    {
        cout << nums[i] << " ";
    }
    cout << endl;

    Solution mySolution;
    mySolution.nextPermutation(nums);
    cout << "Next Permutation: ";
    for (int i = 0; i < nums.size(); i++)
    {
        cout << nums[i] << " ";
    }
    cout << endl;
    system("pause");
    return 0;
}

