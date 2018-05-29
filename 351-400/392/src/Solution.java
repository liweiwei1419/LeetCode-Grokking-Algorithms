
// https://leetcode-cn.com/problems/is-subsequence/description/
// 392. 判断子序列
// 思考这道题和贪心算法有什么关系
public class Solution {

    // 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
    // 感觉自己写得太啰嗦了
    public boolean isSubsequence(String s, String t) {
        int slen = s.length();
        int tlen = t.length();
        if (slen == 0) {
            return true;
        }
        if (slen > tlen) {
            return false;
        }
        int si = 0;
        int ti = 0;
        int sameNum = 0; // s 和 t 相同的数量
        while (si < slen && ti < tlen) {
            if (s.charAt(si) == t.charAt(ti)) {
                si++;
                ti++;
                sameNum++;
                if (si == slen) { // 如果 s 已经全部遍历过
                    // 返回 s 和 t 相同的数量是不是与 s 的字符数相同
                    return sameNum == slen;
                }
            } else {
                ti++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";

        Solution solution = new Solution();
        boolean subsequence = solution.isSubsequence(s, t);
        System.out.println(subsequence);
    }
}