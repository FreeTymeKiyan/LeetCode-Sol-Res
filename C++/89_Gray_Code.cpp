//89. Gray Code
/*
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
00 - 0
01 - 1
11 - 3
10 - 2

Tag: Backtracking

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
#include  <math.h>
using namespace std;

class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> v;
        for (int i = 0; i < 1<<n; i++){
            v.push_back( (i>>1)^i );
        }
        return v;
    }
};

void main(){
    unsigned int num = 0;
    Solution sol;
    vector<int> a = sol.grayCode(num);
}
