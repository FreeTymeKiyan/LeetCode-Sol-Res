//88. Merge Sorted Array
/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Tag: Array, Two pointers

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    void merge(vector<int>& nums1, int m, vector<int>& nums2, int n) {
        int i = m-1, j = n-1, fin = m+n-1;
        while(j >= 0)
            nums1[fin--] = (i >= 0 && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--]; 
    }
};

void main(){
    int n1[] = {1,2,3}; 
    vector<int> nums1(6);
    nums1.at(0) = n1[0];
    nums1.at(1) = n1[1];
    nums1.at(2) = n1[2];
    int n2[] = {1,2,4};
    vector<int> nums2(n2, n2+sizeof(n2)/sizeof(int));
    Solution sol;
    sol.merge(nums1,3,nums2,3);
}
