/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,

 *  Given linked list: 1->2->3->4->5, and n = 2.

 *  After removing the second node from the end, the linked list becomes 1->2->3->5.

 *  Note:
 *  Given n will always be valid.
 *  Try to do this in one pass. 
 * 
 * Date: 03/04/2016
 * Author: SaSa150 
*/

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        ListNode dum(0), *slow=&dum, *fast=head;
        dum.next=head;  //get the dum connected
        
        // move the fast pointer according to the offset
        for(int i=0; i<n; ++i) fast=fast->next;
        while(fast) {
            fast=fast->next;
            slow=slow->next;
        }
        //now the slow pointer will be the n+1 th node from the end (may be the dum head)
        fast=slow->next;
        slow->next=fast->next;
        delete fast;    //remove the specified node
        
        return dum.next;
    }
};
