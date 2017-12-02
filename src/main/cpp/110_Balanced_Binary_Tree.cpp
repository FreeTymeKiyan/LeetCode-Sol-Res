// 110_Balanced_Binary_Tree.cpp : Defines the entry point for the console application.
/**
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Tags: Tree Depth-first Search
 *
 * Similar Problems: (E) Maximum Depth of Binary Tree
 *
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <cstdlib>
#include <algorithm>

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
    // get the height of the tree if it is balanced
    // return -1 if it is not balanced
    int getTreeHeight (TreeNode *root) {
        if (root == NULL)
        {
            return 0;
        }

        if ((root->left == NULL) && (root->right == NULL))
        {
            return 1;
        }

        int lheight = getTreeHeight(root->left);
        int rheight = getTreeHeight(root->right);

        // left and right subtree not balanced or heights differ more than 1
        if (lheight == -1 || rheight == -1 || abs(lheight - rheight) > 1)
        {
            return -1;
        }

        return 1 + max(lheight, rheight);
    }
public:
    bool isBalanced(TreeNode* root) {
        int res = getTreeHeight(root);
        return res != -1;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

