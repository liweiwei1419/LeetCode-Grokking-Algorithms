
public class Solution3 {

    // https://leetcode-cn.com/problems/reverse-linked-list-ii/description/

    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;

        ListNode next;
        for (int i = 0; i < n - m; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
