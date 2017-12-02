//85. Maximal Rectangle
/*
*Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
*
*Subscribe to see which companies asked this question
*
*Tag: Array, Hash Table, Stack, Dynamic Programming
*
*Author: Linsen Wu
*/

#include "stdafx.h"
#include <vector>
#include <stack>

using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char> > &matrix) {
        if(matrix.empty() || matrix[0].empty()) {
            return 0;
        }

        int m = matrix.size();
        int n = matrix[0].size();

        vector<vector<int> > height(m, vector<int>(n, 0));
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '0') {
                    height[i][j] = 0;
                } else {
                    height[i][j] = (i == 0) ? 1 : height[i - 1][j] + 1;
                }
            }
        }

        int maxArea = 0;
        for(int i = 0; i < m; i++) {
            maxArea = max(maxArea, largestRectangleArea(height[i]));
        }
        return maxArea;
    }

    int largestRectangleArea(vector<int>& heights) {                 // see problem 084 Largest Rectangle in Histogram
        stack<int> S;  
        heights.push_back(0);  
        int sum = 0;  
        for (int i = 0; i < heights.size(); i++) {  
            if (S.empty() || heights[i] > heights[S.top()]) {
                S.push(i);
            }
            else {  
                int tmp = S.top();  
                S.pop();  
                sum = max(sum, heights[tmp]*(S.empty()? i : i-S.top()-1));  
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

