//55. Jump Game
/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

For example:
A = [2,3,1,1,4], return true.

A = [3,2,1,0,4], return false.

Author: Xinyu Liu

Tag: Array, Greedy
*/

#include <iostream>
#include <vector>
using namespace std;

class Solution {
public:
    bool canJump(vector<int>& nums) {
        int size_nums = nums.size();
        int i = 0;
        for (int reach = 0; i < size_nums && i <= reach; i++){
            reach = max(nums.at(i) + i, reach);
            if (reach >= size_nums - 1)
                return true;
        }
        return false;
    }
};

void main(){

    int test[5] = {1,2,3,4,5};
    vector<int> vec(test, test + sizeof(test)/sizeof(int));
    Solution sol;
    bool y = sol.canJump(vec);

}
