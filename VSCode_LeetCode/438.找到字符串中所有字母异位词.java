/*
 * @lc app=leetcode.cn id=438 lang=java
 *
 * [438] 找到字符串中所有字母异位词
 */

// @lc code=start
import java.util.*;
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        int[] cnt = new int[26];//词频数组

        //统计p中字符频数cnt
        for (int i = 0; i < m; i++) cnt[p.charAt(i) - 'a']++;

        //统计p中字符种类数a
        int a = 0;
        for (int i = 0; i < 26; i++) if (cnt[i] != 0) a++;

        //使用变量 a 统计 p 中不同字符的数量，
        //使用变量 b 统计滑动窗口（子串）内有多少个字符词频与 p 相等。
        for (int l = 0, r = 0, b = 0; r < n; r++) {
            // 往窗口增加字符，进行词频的抵消操作，如果抵消后词频为 0，说明有一个新的字符词频与 p 完全相等
            if (--cnt[s.charAt(r) - 'a'] == 0) b++; 
            // 若窗口长度超过规定，将窗口左端点右移，执行词频恢复操作，如果恢复后词频为 1（恢复前为 0），说明少了一个词频与 p 完全性相等的字符
            if (r - l + 1 > m && ++cnt[s.charAt(l++) - 'a'] == 1) b--;
            if (b == a) ans.add(l);
        }
        return ans;
    }
}
// @lc code=end

