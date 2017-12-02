// 4 Median of Two Sorted Array 
/**
* There are two sorted arrays nums1 and nums2 of size m and n respectively. 
* Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
* 
* Tag:    Divide and Conquer, Array, Binary Search
*
* Author: Yanbin Lu
*/

#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>
#include <queue>
#include <stack>

using namespace std;

class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        
        int len1 = nums1.size();
        int len2 = nums2.size();
        
        // always consider nums1 is shorter than nums2
        if(len1 > len2) return findMedianSortedArrays(nums2, nums1);
        
        int mid = (len1 + len2 -1) / 2;
        int leftin1 = 0, rightin1 = len1;   // the rightin1 is a boundary, not an actual index
        while(leftin1 < rightin1){
            int mid1 = (leftin1 + rightin1) / 2;    // mid point in array 1
            int mid2 = mid - mid1;                  // mid point in array 2
            if(nums1[mid1] < nums2[mid2])
                leftin1 = mid1 + 1;
            else
                rightin1 = mid1;
        }

        //len1 + len2 is odd
        // median is either nums1[leftin1 - 1] or nums2[mid - leftin1]
        int temp1 = max(leftin1 > 0 ? nums1[leftin1 - 1] : INT_MIN, mid - leftin1 >= 0 ? nums2[mid - leftin1] :INT_MIN);
        if ((len1 + len2) % 2 == 1)
            return (double) temp1;
        
        //len1 + len2 is even
        // median is either nums1[leftin1] or nums2[mid - leftin1 + 1]
        int temp2 = min(leftin1 < len1 ? nums1[leftin1] : INT_MAX, mid - leftin1 + 1 < len2 ? nums2[mid - leftin1 + 1] : INT_MAX);
        return (temp1 + temp2) / 2.0;
    }
};

int main()
{
    vector<int> nums1;
    nums1.push_back(1);
    nums1.push_back(2);
    nums1.push_back(4);
    nums1.push_back(5);

    vector<int> nums2;
    nums2.push_back(3);
    nums2.push_back(3);
    nums2.push_back(4);
    nums2.push_back(5);
    nums2.push_back(6);
    Solution* sol = new Solution();
    cout << sol->findMedianSortedArrays(nums1, nums2) << endl;
	
    char c;
    std::cin>>c;

    return 0;
}

