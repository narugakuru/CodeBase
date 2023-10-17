package juniorSort;

import java.util.Stack;

//暴力解
class CQueue栈实现队列 {
    Stack<Integer> A;
    Stack<Integer> B;

    public CQueue栈实现队列() {
        A = new Stack<Integer>();
        B = new Stack<Integer>();
    }

    public void appendTail(int value) {
        A.push(value);
    }

    public int deleteHead() {
        //把A的数据全转移到B，头尾置换
        while(!A.empty()){
            B.push(A.pop());
        }
        //不为空则输出B的尾数据，B的尾其实就是A的头
        int result = B.empty() ? -1 : B.pop();
        //再把B的数据还给A
        while(!B.empty()){
            A.push(B.pop());
        }
        return result;
    }
}

/*
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
