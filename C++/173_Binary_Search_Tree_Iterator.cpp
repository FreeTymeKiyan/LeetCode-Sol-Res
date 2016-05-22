// 173. Binary Search Tree Iterator
/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * 
 * Calling next() will return the next smallest number in the BST.
 * 
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 * 
 * Tags: Tree, Stack, Design
 * 
 * Similar Problems: (M) Binary Tree Inorder Traversal, (M) Flatten 2D Vector, (M) Zigzag Iterator, (M) Peeking Iterator, (M) Inorder Successor in BST
 * 
 * Author: Kuang Qin
 */

#include "stdafx.h"
#include <stack>
#include <iostream>

using namespace std;

/**
 * Definition for binary tree
 */
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

// Time: O(1), pop the top element on the stack
// Space: O(h), elements in the stack will never exceed the height of the tree
class BSTIterator {
    stack<TreeNode *> st;

    // push all left children onto the stack
    void pushAllNodes(TreeNode *root) {
        while (root != NULL)
        {
            st.push(root);
            root = root->left;
        }
    }
public:
    BSTIterator(TreeNode *root) {
        pushAllNodes(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext() {
        return !st.empty();
    }

    /** @return the next smallest number */
    int next() {
        // pop the smallest element
        TreeNode *node = st.top();
        st.pop();

        // push the right subtree of current node
        pushAllNodes(node->right);
        return node->val;
    }
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

