package a06_PartitionList;

/**
 * Leetcode 86
 * @author Chenyu Liu
 * @since 3/3/25 Monday
 **/

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example 2:
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 *
 * Example 2:
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 *
 * Constraints:
 * The number of nodes in the list is in the range [0, 200].
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode partition(ListNode head, int x) {
        /**
         * 相当于分为lessthen一个list和greaterthanequal一个list
         * 遍历当前list，判断每一个元素，add到对应的list
         * 然后把lessthan的尾部与greaterthanequal的头部连接起来
         * 注意如果lessthan为空那么就直接返回greaterthanequal
         * 注意corner case
         */
        ListNode ltHead = null;
        ListNode ltCurr = ltHead;
        ListNode gteHead = null;
        ListNode gteCurr = gteHead;
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        while(head != null){
            if(head.val < x){
                if (ltHead == null){
                    ltHead = new ListNode(head.val);
                    ltCurr = ltHead;
                }else {
                    ltCurr.next = new ListNode(head.val);
                    ltCurr = ltCurr.next;
                }
            }else{
                if (gteHead == null){
                    gteHead = new ListNode(head.val);
                    gteCurr = gteHead;
                }else {
                    gteCurr.next = new ListNode(head.val);
                    gteCurr = gteCurr.next;
                }
            }
            head = head.next;
        }
        if(ltCurr == null){
            return gteHead;
        }else{
            ltCurr.next = gteHead;
            return ltHead;
        }
    }
}
