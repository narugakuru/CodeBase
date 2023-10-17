package juniorSort;/*

检验一个字符串中是否有重复出现的字符，字符串只能包括26个小写字母
例如：
    输入：hello
    输出：false
*/

/*
 * 解题思路：与位运算
 * 参考ascii表，记住ascii值，例如A为65，a为97,z为122等
 * int为4字节32位，可用于存储26个字节，开始使int置为0
 * 计算字符距离a的Ascii偏移量move，a的则将第一个bit置为1，b则是000...000010,c是000...000100
 *
 * */

public class BitOperation位运算 {
    public static void main(String[] args) {
        String str = "hello";

        System.out.println(Solution(str));

//        test01();

    }

    public static boolean Solution(String str) {
        long tmp26 = 0;
        for (char c : str.toCharArray()) {
            long bitIndex = 1L << c; //左移c位,1L代表二进制的1
            if ((tmp26 & bitIndex) != 0) {//异或运算，判断是否出现过01=0，11=0，00=0
                return false;
            }
            tmp26 |= bitIndex;//按位或，即并集
        }
        return true;
    }

    public static void test01() {
        char c = 'a';
        Long bit = 1L << 'a';
        System.out.println(bit);
    }

    public static void printL(){//输出二进制序列
        int num = 3;
        for (int i = 31; i >= 0; i--) {
            System.out.print(((num >> i) & 1) + " ");
        }
        System.out.println();
    }

}



/*
    def isUnique(self, astr: str) -> bool:
        mark = 0
        for char in astr:
        move_bit = ord(char) - ord('a')
        if (mark & (1 << move_bit)) != 0:
        return False
        else:
        mark |= (1 << move_bit)
        return True*/

/*
*   public boolean isUnique(String astr) {
        long low64 = 0;
        long high64 = 0;

        for (char c : astr.toCharArray()) {
            if (c >= 64) {
                long bitIndex = 1L << (c - 64);
                if ((high64 & bitIndex) != 0) {
                    return false;
                }

                high64 |= bitIndex;
            } else {
                long bitIndex = 1L << c;
                if ((low64 & bitIndex) != 0) {
                    return false;
                }

                low64 |= bitIndex;
            }

        }

        return true;
    }
*/