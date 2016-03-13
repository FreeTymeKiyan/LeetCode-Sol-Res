//84. Largest Rectangle in Histogram
/*
 *Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
 *
 *
 *Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 *
 *
 *The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 *For example,
 *Given heights = [2,1,5,6,2,3],
 *return 10.
 *
 *Tag: Array, Stack
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <vector>
#include <stack>

using namespace std;

class Solution {
public:
    int largestRectangleArea(vector<int>& heights) {
        stack<int> MtSt;  
        heights.push_back(0);  
        int sum = 0;  
        for (int i = 0; i < heights.size(); i++) {  
            if (MtSt.empty() || heights[i] > heights[MtSt.top()]) {
                MtSt.push(i);
            }
            else {  
                int tmp = MtSt.top();  
                MtSt.pop();  
                sum = max(sum, heights[tmp]*(MtSt.empty()? i : i-MtSt.top()-1));  
                i--;  
            }  
        }  
        return sum;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

