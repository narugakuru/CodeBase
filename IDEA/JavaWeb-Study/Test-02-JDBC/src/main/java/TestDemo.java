import org.junit.jupiter.api.Test;

public class TestDemo {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode new_head = recursion(head.next);
        head.next.next = head;
        head.next = null;

        return new_head;
    }

    @Test
    public static void t() {
        ListNode node3 = new ListNode(3,null);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

        ListNode recursion = recursion(node1);
        System.out.println(recursion);
    }
}
