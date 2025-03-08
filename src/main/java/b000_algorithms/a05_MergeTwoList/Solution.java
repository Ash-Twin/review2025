package main.java.b000_algorithms.a05_MergeTwoList;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class Solution {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
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

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //corner case
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.val >= list2.val ? list2 : list1;
        ListNode pointer1 = head.next;
        ListNode pointer2 = head == list1 ? list2 : list1; // 比较的是当前头的next与另一个list的当前pointer
        ListNode currHead = head;
        while (pointer1 != null && pointer2 != null) {
            if (pointer1.val >= pointer2.val) {
                currHead.next = pointer2;
                pointer2 = pointer2.next;
            } else {
                currHead.next = pointer1;
                pointer1 = pointer1.next;
            }
            currHead = currHead.next;
        }
        currHead.next = pointer1 == null ? pointer2 : pointer1; //最后处理链表尾部最后一个元素，就是pointer1或者pointer2有一个是空的时候
        return head;
    }
}
