package Test15;

import org.junit.jupiter.api.Test;

public class StringReplace {
    @Test
    public void replaceSpace() {
        String s = "We are happy";
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()){
            if (c == ' '){
                sb.append('%');
                sb.append('2');
                sb.append('0');
            }else {
                sb.append(c);
            }
        }
        s=sb.toString();
        System.out.println(s);

    }

//    链表
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }



    public int[] reversePrint(ListNode head) {
        ListNode cur = head;
        int count = 0;

        while (cur.next!= null){
            cur = cur.next;
            count++;
        }
        int[] arr = new int[count];

        cur = head;
        while (cur.next!=null){
            arr[--count] = cur.val;
            cur = cur.next;
        }

        return arr;
    }
}


