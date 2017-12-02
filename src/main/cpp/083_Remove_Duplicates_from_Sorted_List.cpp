//083 Remove Duplicates from Sorted List
/*
 *Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 *For example,
 *Given 1->1->2, return 1->2.
 *Given 1->1->2->3->3, return 1->2->3.
 *
 *Tag: Linked List
 *
 *Author: Linsen Wu
 */

#include "stdafx.h"

 //Definition for singly-linked list.
struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};

class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
		if (head  == NULL)
		{
			return NULL;
		}

		ListNode* current = head->next;
		ListNode* previous = head;

		while (current != NULL)
		{
			if (previous->val == current->val)
			{
				current = current->next;
				if (current == NULL)
				{
					previous->next = NULL;
				}

			} else
			{
				previous->next = current;
				previous = current;
				current = current->next;
			}
		}

		return head;
    }
};


int _tmain(int argc, _TCHAR* argv[])
{
	return 0;
}

