package juniorSort;

/*
0 + 0 = 0
0 + 1 = 1
1 + 0 = 1
1 + 1 = 0 (进位)
可以发现，对于整数 aa 和 bb：

在不考虑进位的情况下，其无进位加法结果为 a⊕b。

而所有需要进位的位为 a & b，进位后的进位结果为 (a & b) << 1。

于是，我们可以将整数 aa 和 bb 的和，拆分为 aa 和 bb 的无进位加法结果与进位结果的和。
因为每一次拆分都可以让需要进位的最低位至少左移一位，又因为 aa 和 bb 可以取到负数，
所以我们最多需要 log(max_int) 次拆分即可完成运算。
*/

/*
按位运算符有6个
& 按位与
|按位或
^按位异或
~取反
>>右移
<<左移
* */

public class Bit位运算实现加减 {

    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1; //与
            a = a ^ b; //异或
            b = carry;
        }
        return a;
    }

}
