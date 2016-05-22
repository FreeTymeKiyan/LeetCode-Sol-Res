// 141 Linked List Cycle
/**
* Given a linked list, determine if it has a cycle in it.
*
* Follow up:
* Can you solve it without using extra space?
*
* Tag:     Linked List, Two Pointers
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

using namespace std;
struct ListNode {
     int val;
     ListNode *next;
     ListNode(int x) : val(x), next(NULL) {}
 };
 
class Solution {
public:
    bool hasCycle(ListNode *head) {
        ListNode* slow = head;
        ListNode* fast = head;
        while(fast && fast->next){
            fast = fast->next->next;
            slow = slow->next;
            if(fast == slow) return true;
        }
        return false;
    }
};

int main()
{
	// creat a cycle list
	ListNode* list = new ListNode(3);
	list->next = new ListNode(2);
	list->next->next = new ListNode(1);
	list->next->next->next = new ListNode(4);
	list->next->next->next->next = list;

	Solution* sol = new Solution();
	cout<<sol->hasCycle(list)<<std::endl;
	
	char c;
	std::cin>>c;

	return 0;
}