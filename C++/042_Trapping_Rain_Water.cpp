//42. Trapping Rain Water
/**
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
 * 
 * For example, 
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!
 * 
 * Tags: Array, Stack, Two Pointers
 * 
 * Similar Problems: (M) Container With Most Water, (M) Product of Array Except Self
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>

using namespace std;

// O(n) time, O(1) space
class Solution {
public:
    int trap(vector<int>& height) {
        int n = height.size();

        if (n < 3)
        {
            return 0;
        }

        // scan from left to right, consider only left side
        int lheight = 0, water = 0;
        for (int i = 0; i < n; i++)
        {
            if (height[i] < lheight)
            {
                water += lheight - height[i];
            }
            else
            {
                lheight = height[i];
            }
        }

        // scan from right to left, substract height difference
        int rheight = 0;
        for (int i = n - 1; i >= 0; i--)
        {
            rheight = max(height[i], rheight);
            if (rheight < lheight)
            {
                water -= lheight - rheight;
            }
        }

        return water;
    }
};

// Two Pointers: O(n) time, O(1) space
class Solution_TwoPointers {
public:
    int trap(vector<int>& height) {
        int n = height.size();

        if (n < 3)
        {
            return 0;
        }

        // scan from left and right
        int left = 0, right = n - 1, highest = 0, water = 0;
        while (left < right)
        {
            // get min(height[left], height[right]) as current highest level
            int curr = height[left] < height[right] ? height[left++] : height[right--];
            if (curr < highest)
            {
                // add trapped water
                water += highest - curr;
            }
            else
            {
                highest = curr;    // update highest level
            }
        }

        return water;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    vector<int> height;
    // 0,1,0,2,1,0,1,3,2,1,2,1
    height.push_back(0);
    height.push_back(1);
    height.push_back(0);
    height.push_back(2);
    height.push_back(1);
    height.push_back(0);
    height.push_back(1);
    height.push_back(3);
    height.push_back(2);
    height.push_back(1);
    height.push_back(2);
    height.push_back(1);
    Solution_TwoPointers mySolution;
    int res = mySolution.trap(height);
    return 0;
}
