// 96 Unique Binary Search Trees 
/**
* Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
*
* For example,
* Given n = 3, there are a total of 5 unique BST's.
*
*    1         3     3      2      1
*     \       /     /      / \      \
*      3     2     1      1   3      2
*     /     /       \                 \
*    2     1         2                 3
*
* Tag:    Tree, Dynamic Programming
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

using namespace std;

class Solution {
public:
    int numTrees(int n) {
        if(n<=1) return 1;
        vector<int> dp(n+1,0);
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i<=n;i++)
            // set i as the root compute the number of trees on left side and right side
            for(int j = 1; j<=i;j++)
                dp[i] += dp[j-1]*dp[i-j];
        return dp[n];
    }
};

int main()
{

	Solution* sol = new Solution();

    cout << sol->numTrees(5) << endl;
    cout << sol->numTrees(1) << endl;
    cout << sol->numTrees(500) << endl;
	
    char c;
    std::cin>>c;

    return 0;
}

