// 101. Symmetric Tree
/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * For example, this binary tree is symmetric:
 * 
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 * 
 * Tags: Tree, Depth-first Search
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <queue>
#include <stack>

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

// recursive
class Solution {
    bool isTwoSym(TreeNode *left, TreeNode *right)
    {
        // both left and right nodes are null
        if ((left == NULL) && (right == NULL))
        {
            return true;
        }

        // only one node is null
        if ((left == NULL) || (right == NULL))
        {
            return false;
        }

        // both left and right nodes are not null
        if (left->val != right->val)
        {
            return false;
        }

        // consider mirror symmetric
        //      left    |    right
        //     /    \   |   /     \
        //    1      2  |  3       4
        // compare 1 & 4, 2 & 3
        return isTwoSym(left->left, right->right) && isTwoSym(left->right, right->left);
    }

public:
    bool isSymmetric(TreeNode* root) {
        if (root == NULL)
        {
            return true;
        }

        return isTwoSym(root->left, root->right);
    }
};

// iterative: bfs
class Solution_BFS {
public:
    bool isSymmetric(TreeNode* root) {
        if (root == NULL)
        {
            return true;
        }

        // push mirror pairs into the queue, first in first out
        queue<TreeNode *> lq, rq;
        lq.push(root->left);
        rq.push(root->right);

        while (!lq.empty() && !rq.empty())
        {
            TreeNode *left = lq.front(), *right = rq.front();
            lq.pop();
            rq.pop();
           
            // both left and right nodes are null
            if ((left == NULL) && (right == NULL))
            {
                continue;
            }

            // only one node is null
            if ((left == NULL) || (right == NULL))
            {
                return false;
            }

            // both left and right nodes are not null
            if (left->val != right->val)
            {
                return false;
            }
            
            lq.push(left->left);
            rq.push(right->right);
            lq.push(left->right);
            rq.push(right->left);
        }

        return true;
    }
};

// iterative: dfs
class Solution_DFS {
public:
    bool isSymmetric(TreeNode* root) {
        if (root == NULL)
        {
            return true;
        }

        // first compare left->left to right->right, then compare left->right to right->left
        // first in last out here, use stack
        stack<TreeNode *> lstack, rstack;
        lstack.push(root);
        rstack.push(root);

        // start from root->left and root->right
        TreeNode *left = root->left, *right = root->right;
        while (!lstack.empty() && !rstack.empty())
        {
            // both left and right nodes are null
            // go back to parent node to search the other side
            if ((left == NULL) && (right == NULL))
            {
                left = lstack.top()->right;
                right = rstack.top()->left;
                lstack.pop();
                rstack.pop();
                continue;
            }

            // only one node is null
            if ((left == NULL) || (right == NULL))
            {
                return false;
            }

            // both left and right nodes are not null
            if (left->val != right->val)
            {
                return false;
            }
            
            // push current node onto stack
            lstack.push(left);
            rstack.push(right);

            // go to next level
            left = left->left;
            right = right->right;
        }

        return true;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

