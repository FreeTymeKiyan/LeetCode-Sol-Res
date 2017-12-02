// 104 Maximum Depth of Binary Tree
/**
* Given a binary tree, find its maximum depth.
*
* The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
 
class Solution {
public:
    int maxDepth(TreeNode* root) {
        if(root)
            return 1 + max(maxDepth(root->left), maxDepth(root->right));
        else 
            return 0;
    }
};

int main()
{
    
    TreeNode* root = new TreeNode(0);
    root->left = new TreeNode(1);
    root->right = new TreeNode(2);
    root->left->right = new TreeNode(3);
    
    Solution* sol = new Solution();
    cout << sol->maxDepth(root) << endl;
	
    char c;
    std::cin>>c;

    return 0;
}