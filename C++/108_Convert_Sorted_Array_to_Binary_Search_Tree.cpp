// 108. Convert Sorted Array to Binary Search Tree
/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * Tags: Tree, Depth-first Search
 *
 * Similar Problems: (M) Convert Sorted List to Binary Search Tree
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>

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
    TreeNode* dfs(vector<int>& nums, int start, int end) {
        if (start > end)
        {
            return NULL;
        }

        int mid = (start + end) / 2;
        TreeNode *root = new TreeNode(nums[mid]);

        root->left = dfs(nums, start, mid - 1);
        root->right = dfs(nums, mid + 1, end);

        return root;
    }
public:
    TreeNode* sortedArrayToBST(vector<int>& nums) {
        int n = nums.size();
        if (n == 0)
        {
            return NULL;
        }

        return dfs(nums, 0, n - 1);
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}
