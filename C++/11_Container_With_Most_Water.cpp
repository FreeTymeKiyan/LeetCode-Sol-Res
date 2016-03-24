//11. Container With Most Water
/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) 
and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.

Tag: Array, Two Pointers

Author: Xinyu Liu
*/
#include <iostream>
#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    int maxArea(vector<int>& height) {
        if(height.empty()) return 0;
        int max_container = 0;
        int i = 0, h = 0;
        int j = height.size() - 1;
        while (i < j){
             h = min(height[i], height[j]);
             max_container = max(max_container, (j - i) * h);
             while (height[i] <= h && i < j) i++;
             while (height[j] <= h && i < j) j--;
        }
        return max_container;
    }
};

void main(){
    int a[] = {1,2,4,3};
    vector<int> height(a, a + sizeof(a)/sizeof(int));
    Solution sol;
    int max_container = sol.maxArea(height); 
}
