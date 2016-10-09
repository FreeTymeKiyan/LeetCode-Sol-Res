/** Problem description
   Given a sorted linked list, delete all nodes that have duplicate
      numbers,leaving only distinct numbers from the original list.

   For example,
   Given 1->2->3->3->4->4->5, return 1->2->5.
   Given 1->1->1->2->3, return 2->3.
 */

/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */

/** Solution
   add a sentinel node(newHead), the node stores current duplicate number,
   delete all the other list nodes whose values equal to newHead->val.
   update newHead->val until meet the next duplicate number .

   Time O(n)
   Space O(1)
 */
class Solution {
public:

  ListNode* deleteDuplicates(ListNode *head) {
    if (!head || !(head->next)) return head;


    ListNode *newHead = new ListNode(head->val - 1);
    newHead->next = head;
    ListNode *p = newHead;

    while (p->next && p->next->next) {
      if (p->next->val == p->next->next->val) {
        newHead->val = p->next->val;

        while (p->next && newHead->val == p->next->val) {
          // TODO: free memory pointed by p->next
          p->next = p->next->next;
        }
      }
      else p = p->next;
    }
    return newHead->next;
  }
};
