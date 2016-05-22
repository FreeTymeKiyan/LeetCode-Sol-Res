// 145 Binary Tree PostOrder Tranversal
/**
* Given a binary tree, return the postorder traversal of its nodes' values.
* 
* For example:
* Given binary tree {1,#,2,3},
*    1
*     \
*      2
*     /
*    3
* return [3,2,1].
* 
* Note: Recursive solution is trivial, could you do it iteratively?
* 
* Tag:    Tree, Stack    
* 
* Author: Yanbin Lu
*/

#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>
#include <queue>
#include <stack>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    vector<int> postorderTraversal(TreeNode* root) {
        vector<int> res;
        if(!root) return res;
        
        stack<TreeNode*> s;
        s.push(root);
        TreeNode* pre = NULL;
        TreeNode* cur;
        while (!s.empty()) {
            cur = s.top();
            
            // !pre coresponds to the root case, otherwise going down
            if(!pre || pre->left == cur || pre->right == cur){
                if(cur->left)               // to left child if exist
                    s.push(cur->left);      // t right child if left child doesnt exist
                else if(cur->right)
                    s.push(cur->right);
                else{                       // this is the leaf
                    s.pop();
                    res.push_back(cur->val);
                }
            }
            
            // going up from the left child
            else if(cur->left == pre){
                if(cur->right)
                    s.push(cur->right);      // to the right child if exist
                else{
                    s.pop();                  
                    res.push_back(cur->val);
                }
            }
            
            // going up from the right side, done from this point dowm
            else if(cur->right == pre){
                s.pop();
                res.push_back(cur->val);
            }
            
            pre = cur;
        }
        
        return res;
    }
};

int main()
{

    TreeNode* root = new TreeNode(2);
    root->left = new TreeNode(3);
    root->right = new TreeNode(4);
    root->left->right = new TreeNode(5);
    Solution* sol = new Solution();
    vector<int> res = sol->postorderTraversal(root);
    for(int i = 0; i < res.size(); i++)
        cout << res[i] << endl;
	
    char c;
    std::cin>>c;

    return 0;
}

