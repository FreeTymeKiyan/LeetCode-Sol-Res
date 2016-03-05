// 105. Construct Binary Tree from Preorder and Inorder Traversal
/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note: You may assume that duplicates do not exist in the tree.

Tag: Tree, Array, Depth-first-search

Author: Xinyu Liu
*/

#include <iostream>
#include <vector>
using namespace std;


//  Definition for a binary tree node.
struct TreeNode {
	int val;
	TreeNode *left;
	TreeNode *right;
	TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
	TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
		return subtree(preorder, 0, preorder.size() - 1 , inorder, 0, preorder.size() - 1);
	}
	TreeNode* subtree(vector<int>& preorder, int begin1, int end1, vector<int>& inorder, int begin2, int end2){
		int i;
		if(end1 < begin1)
			return NULL;
		if(end1 == begin1)
			return new TreeNode(preorder.at(begin1));

		TreeNode* root = new TreeNode(preorder.at(begin1));
		for(i = begin2; i < end2; i++){
			if(inorder.at(i) == root->val)
				break;
		}
		int left_length = i - begin2;
		root->left = subtree(preorder, begin1 + 1, begin1 + left_length, inorder, begin2, begin2 + left_length - 1);
		root->right = subtree(preorder, begin1 + left_length + 1, end1, inorder, begin2 + left_length + 1, end2);
		return root;
	}
};

void main(){
	
	int pre[] = {1,2,3,4};
	vector<int> preorder(pre, pre + sizeof(pre) / sizeof(int));
	int in[] = {2,1,4,3};
	vector<int> inorder(in, in + sizeof(in) / sizeof(int));
	Solution sol;
	TreeNode* root = sol.buildTree(preorder, inorder);

}
