// 102. Binary Tree Level Order Traversal
/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * 
 * Tags: Tree, Breadth-first Search
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <vector>
#include <queue>

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
public:
    vector<vector<int>> levelOrder(TreeNode* root) {
        vector<vector<int>> res;
        if (root == NULL)
        {
            return res;
        }

        // use queue - first in first out
        queue<TreeNode *> q;

        q.push(root);

        while(!q.empty())
        {
            vector<int> level;
            int currLevelCount = q.size();

            // bfs for current level
            while (currLevelCount--)
            {
                TreeNode* curr = q.front();
                level.push_back(curr->val);

                if (curr->left != NULL)
                {
                    q.push(curr->left);
                }

                if (curr->right != NULL)
                {
                    q.push(curr->right);
                }

                q.pop();
            }

            // when the counter equals zero, add current level to result
            res.push_back(level);
        }

        return res;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

