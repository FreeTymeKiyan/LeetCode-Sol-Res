//128. Longest Consecutive Sequence
/*
*Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
*
*For example,
*Given [100, 4, 200, 1, 3, 2],
*The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
*
*Your algorithm should run in O(n) complexity.
*
*Tag: Array, Union Find
*
*Author: Linsen Wu
*/

#include "stdafx.h"
#include <vector>
#include <unordered_set>

using namespace std;

class Solution {
public:
    int longestConsecutive(vector<int> &nums) {
        if(nums.empty()) return 0;
        unordered_set<int> ht;
        for(int i=0; i<nums.size(); i++)
            ht.insert(nums[i]);
            
        int maxLen = 1;
        for(int i=0; i<nums.size(); i++) {
            if(ht.empty()) break;
            int curLen = 0;
            int curnums = nums[i];
            
            while(ht.count(curnums)) {
                ht.erase(curnums);
                curLen++;
                curnums++;
            }
            
            curnums = nums[i]-1;
            while(ht.count(curnums)) {
                ht.erase(curnums);
                curLen++;
                curnums--;
            }
            
            maxLen = max(maxLen, curLen);
        }
        
        return maxLen;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

