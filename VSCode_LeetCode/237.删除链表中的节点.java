/* * @lc app=leetcode.cn id=237 lang=java
 *
 * [237] 删除链表中的节点
*/


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// @lc code=start
class S {
    public void deleteNode(ListNode node) {
        System.out.println(node.toString());
        node.val= 34;
        node = node.next;
        node.val = 532;
    }
}
// @lc code=end

