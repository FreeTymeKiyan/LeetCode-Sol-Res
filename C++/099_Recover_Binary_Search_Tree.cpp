//99. Recover Binary Search Tree
/* 
 *Two elements of a binary search tree (BST) are swapped by mistake.
 *
 *Recover the tree without changing its structure.
 *
 *Tag: Tree, Depth-first Search
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"
#include <vector>
#include <algorithm>

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
    //O(n) space solution
    void recoverTree(TreeNode* root) {           
        vector<TreeNode*> list;  
        vector<int > vals;  
        InOrderTravel(root, list, vals);  
        sort(vals.begin(), vals.end());  
        for(int i =0; i< list.size(); i++)  
        {  
            list[i]->val = vals[i];  
        }            
    }

    void InOrderTravel(TreeNode* node, vector<TreeNode*>& list, vector<int>& vals) {  
        if(node == NULL) return;  
        InOrderTravel(node->left, list, vals);  
        list.push_back(node);  
        vals.push_back(node->val);  
        InOrderTravel(node->right, list, vals);            
    }

    //O(1) space solution
    void recoverTree_O1(TreeNode *root) {     
        TreeNode *f1=NULL, *f2=NULL;
        TreeNode  *current,*pre, *parent=NULL;

        if(root == NULL)
            return;

        bool found = false;
        current = root;
        while(current != NULL) {                
            if(current->left == NULL) {
                if(parent && parent->val > current->val) {
                    if(!found) {
                        f1 = parent;
                        found = true;
                    }
                    f2 = current;
                }
                parent = current;
                current = current->right;     
            }   
            else {
                /* Find the inorder predecessor of current */
                pre = current->left;
                while(pre->right != NULL && pre->right != current)
                    pre = pre->right;

                /* Make current as right child of its inorder predecessor */
                if(pre->right == NULL) {
                    pre->right = current;
                    current = current->left;
                }

                /* Revert the changes made in if part to restore the original
                tree i.e., fix the right child of predecssor */  
                else {
                    pre->right = NULL;
                    if(parent->val > current->val) {
                        if(!found) {
                            f1 = parent;       
                            found = true;
                        }
                        f2 = current;
                    }
                    parent = current;
                    current = current->right;     
                } /* End of if condition pre->right == NULL */
            } /* End of if condition current->left == NULL*/
        } /* End of while */

        if(f1 && f2)
            swap(f1->val, f2->val);
    }

    // Function to traverse binary tree without recursion and without stack 
    /*
    vector<int> inorderTraversal(TreeNode *root)
    {
        vector<int> result;  
        TreeNode  *current,*pre;

        if(root == NULL)
            return result;

        current = root;
        while(current != NULL) {                
            if(current->left == NULL) {
                result.push_back(current->val);
                current = current->right;     
            }   
            else {
                // Find the inorder predecessor of current 
                pre = current->left;
                while(pre->right != NULL && pre->right != current)
                    pre = pre->right;

                // Make current as right child of its inorder predecessor
                if(pre->right == NULL) {
                    pre->right = current;
                    current = current->left;
                }

                // Revert the changes made in if part to restore the original tree i.e., fix the right child of predecssor  
                else {
                    pre->right = NULL;
                    result.push_back(current->val);
                    current = current->right;     
                } // End of if condition pre->right == NULL 
            } // End of if condition current->left == NULL
        } // End of while 

        return result;
    } */
}
};

int _tmain(int argc, _TCHAR* argv[])
{
    return 0;
}

