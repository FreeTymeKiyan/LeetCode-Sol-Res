// 124. Binary Tree Maximum Path Sum
/**
 * Given a binary tree, find the maximum path sum.
 *
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *
 *       1
 *      / \
 *     2   3
 * Return 6.
 *
 * Tags: Tree, Depth-first Search
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <windows.h>

using namespace std;

/**
 * Definition for a binary tree node.
 */
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
    int curMax;

public:
    int maxPathSum(TreeNode* root) {
        curMax = INT_MIN;
        dfs(root);
        return curMax;
    }

// if we look at each individual nodes, there are only 3 possiblities:
//   a) Left branch:   root->val + left
//   b) Right branch:  root->val + right
//   c) Both branches: root->val + left + right
//
// a) or b): it can be accumulated between different nodes, so we use them as the return value in the recursion
// c):       it can be only a local maximum, so we compare it to the global maximum and keep whichever is larger

    int dfs(TreeNode* root) {
        if (!root) return 0;

        int left  = max(dfs(root->left), 0);    // if left child val < 0, keep only the parent node value
        int right = max(dfs(root->right), 0);   // if right child val < 0, keep only the parent node value
        curMax = max(curMax, root->val + left + right);  // compare the global max to possible local max

        return root->val + max(left, right);    // choose whichever is larger
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}

