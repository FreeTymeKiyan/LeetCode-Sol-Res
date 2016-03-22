//100 Same Tree
/*
 *Given two binary trees, write a function to check if they are equal or not.
 *
 *Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 *
 *Tag: Tree, Depth-first Search
 *
 *Author: Linsen Wu
 * 
 */

#include "stdafx.h"

using namespace std;

//Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    bool isSameTree(TreeNode *p, TreeNode *q) {  
        if(!p && !q) return true;  
        if(!p || !q) return false;  
        return (p->val == q->val) &&  
            isSameTree(p->left, q->left) &&   
            isSameTree(p->right, q->right);      
    } 
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

