// 199 Binary Tree Right Side View
/**
* Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
* 
* For example:
* Given the following binary tree,
*    1            <---
*  /   \
* 2     3         <---
*  \     \
*   5     4       <---
* You should return [1, 3, 4].
*
* Tag:     Tree, Depth First Search, Breadth First Search
*
* Author:  Yanbin Lu
*/
#include <stddef.h>
#include <vector>
#include <string.h>
#include <stdio.h>
#include <algorithm> 
#include <iostream>
#include <map>
#include <queue>

using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 };

class Solution {
public:
    vector<int> rightSideView(TreeNode* root) {
        vector<int> res;
        if(!root) return res;
        
        // using level order traverse
        // use a queue to store all nodes in the current level
        queue<TreeNode*> q;
        q.push(root);
        while(!q.empty()){
            int n = q.size();
            for(int i = 0; i < n;i++){
                TreeNode* node = q.front();
                q.pop();
                if(node->left) q.push(node->left);
                if(node->right) q.push(node->right);
                
                // push the last node (right most node) to res
                if(i == n-1) res.push_back(node->val);
            }
        }
        return res;
    }
};

int main()
{
	// creat a binary tree
	TreeNode* root = new TreeNode(0);
	root->left = new TreeNode(3);
	root->right = new TreeNode(2);
	root->left->right = new TreeNode(5);

	Solution* sol = new Solution();
	vector<int> res = sol->rightSideView(root);

	for(int i = 0; i < res.size(); i++)
        cout<<res[i]<<std::endl;
	
	char c;
	std::cin>>c;

	return 0;
}

