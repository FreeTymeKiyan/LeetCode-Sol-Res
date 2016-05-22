// 82 Remove Duplicates from Sorted List II
/**
* Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
*
* For example,
* Given 1->2->3->3->4->4->5, return 1->2->5.
* Given 1->1->1->2->3, return 2->3.
*
* Tag:      Linked List
*
* Author:   Yanbin Lu
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

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        // check empty or single node list
        if(!head || !head->next) return head;
        
        // if the first element is not a duplicate, recursive start from the next one
        if(head->val != head->next->val){
            head->next = deleteDuplicates(head->next);
            return head;
        }
        
        // if the first element is duplicate, go along untill a different value
        else{
            int val = head->val;
            ListNode* node = head;
            while(node && node->val == val) node = node->next;
            return deleteDuplicates(node);
        }
    }
};

int main()
{
    // creat a duplicate list 
    ListNode* list = new ListNode(0);
    list->next = new ListNode(0);
    list->next->next = new ListNode(1);
    list->next->next->next = new ListNode(1);
    list->next->next->next->next = new ListNode(2);
    list->next->next->next->next->next = new ListNode(3);

    Solution* sol = new Solution();
    ListNode* test = sol->deleteDuplicates(list);

    ListNode* node = test;
    while(node){
        cout<<node->val<<endl;
        node = node->next;
    }
	
	char c;
	std::cin>>c;

	return 0;
}

