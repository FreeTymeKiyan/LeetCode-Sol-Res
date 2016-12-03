/** Problem description
   Given a list, rotate the list to the right by k places, where k is
      non-negative.

   For example:
   Given 1->2->3->4->5->NULL and k = 2,
   return 4->5->1->2->3->NULL.
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
   step 1. find the length n of the linked list
   step 2. update k = k%n
   step 3. rotate list
 */
class Solution {
public:

  ListNode* rotateRight(ListNode *head, int k) {
    if (!head || !(head->next)) return head;

    ListNode *p = head;
    int n       = 0;

    while (p) {
      n++;
      p = p->next;
    }
    k = k % n;

    if (k == 0) return head;

    ListNode *p1;
    p = p1 = head;

    while (p1 && k--) p1 = p1->next;

    while (p1 && p1->next) {
      p  = p->next;
      p1 = p1->next;
    }
    p1->next = head;
    head  = p->next;
    p->next  = NULL;
    return head;
  }
};
