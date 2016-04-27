// 116. Populating Next Right Pointers in Each Node
/**
 * Given a binary tree
 * 
 *     struct TreeLinkNode {
 *       TreeLinkNode *left;
 *       TreeLinkNode *right;
 *       TreeLinkNode *next;
 *     }
 *
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 * 
 * Initially, all next pointers are set to NULL.
 * 
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * 
 * For example,
 * Given the following perfect binary tree,
 *
 *          1
 *        /  \
 *       2    3
 *      / \  / \
 *     4  5  6  7
 * 	
 * After calling your function, the tree should look like:
 *
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \  / \
 *     4->5->6->7 -> NULL
 * 
 * Tags: Tree, Depth-first Search
 *
 * Similar Problems: (H) Populating Next Right Pointers in Each Node II, (M) Binary Tree Right Side View
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"

/**
 * Definition for binary tree with next pointer.
 */
struct TreeLinkNode {
    int val;
    TreeLinkNode *left, *right, *next;
    TreeLinkNode(int x) : val(x), left(NULL), right(NULL), next(NULL) {}
};

class Solution {
public:
    void connect(TreeLinkNode *root) {
        while (root != NULL && root->left != NULL)
        {
            // using constant space curr to store current node
            // this for loop is building a list for the next level
            for (TreeLinkNode *curr = root; curr != NULL; curr = curr->next)
            {
                curr->left->next = curr->right;
                if (curr->next != NULL)
                {
                    curr->right->next = curr->next->left;
                }
            }

            // go to the next level
            root = root->left;
        }

        return;
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

