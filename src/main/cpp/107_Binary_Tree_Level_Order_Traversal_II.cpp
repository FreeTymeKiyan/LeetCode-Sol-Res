// 107. Binary Tree Level Order Traversal II
/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * 
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
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
    // see 102. Binary Tree Level Order Traversal
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
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

        vector<vector<int>> result;
        while(res.size() > 0)
        {
            result.push_back(res.back());
            res.pop_back();
        }
        return result;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

