// 117. Populating Next Right Pointers in Each Node II
/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * 
 * Note:
 * You may only use constant extra space.
 * 
 * For example,
 * Given the following binary tree,
 * 
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *
 * After calling your function, the tree should look like:
 * 
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * Tags: Tree, Depth-first Search
 * 
 * Similar Problems: (M) Populating Next Right Pointers in Each Node
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
        while (root != NULL)
        {
            TreeLinkNode dummy(0);
            TreeLinkNode *head = &dummy;

            // build a dummy link list for the next level
            for (TreeLinkNode *curr = root; curr != NULL; curr = curr->next)
            {
                // add left child to the list if not null
                if (curr->left != NULL)
                {
                    head->next = curr->left;
                    head = head->next;
                }

                // add right child to the list if not null
                if (curr->right != NULL)
                {
                    head->next = curr->right;
                    head = head->next;
                }
            }

            // move to the head of next level
            root = (&dummy)->next;
        }
    }
};

int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}

