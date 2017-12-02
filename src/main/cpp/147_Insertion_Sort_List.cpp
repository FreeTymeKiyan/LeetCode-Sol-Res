//147. Insertion Sort List
/*
Sort a linked list using insertion sort.

Author: Xinyu Liu
*/


#include <iostream>
using namespace std;

//Definition for singly-linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};


class Solution{
public:
    ListNode* insertionSortList(ListNode* head){
        ListNode L(INT_MIN);
        L.next = head;

        if(!head||!head->next)
        {
            return head;
        }
        head = head->next;
        L.next->next = NULL;

        ListNode* i;
        while (head){
            i = &L;
            while (i ->next && i ->next ->val < head ->val){
                i = i->next;
            }
            ListNode* insert = head;
            head = head ->next;
            insert->next = i ->next;
            i ->next = insert;
            
        }
        return L.next;

    }
};
