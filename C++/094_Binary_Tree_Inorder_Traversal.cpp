/*
 *Given a binary tree, return the inorder traversal of its nodes' values.

 *For example:
 *Given binary tree {1,#,2,3},
 *   1
 *    \
 *     2
 *    /
 *   3
 *return [1,3,2].

 *Note: Recursive solution is trivial, could you do it iteratively?

 *Tag: Tree, Hash Table, Stack

 *Author: Linsen Wu
*/
#include "stdafx.h"
#include <vector>

using namespace std;

// Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<int> inorderTraversal(TreeNode* root) {
        vector<int> result;
        if (root == NULL)
        {
            return result;
        }
        vector<TreeNode* > stack;
        TreeNode* node = root;

        while (!stack.empty() || node != NULL)
        {
            while (node != NULL)
            {
                stack.push_back(node);
                node = node->left;
            }

            node = stack.back();
            stack.pop_back();
            result.push_back(node->val);

            node = node->right;
        }
        
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

