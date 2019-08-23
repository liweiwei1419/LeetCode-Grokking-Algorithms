/**
 * 题解地址：https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/he-bing-yi-hou-zhao-gui-bing-guo-cheng-zhong-zhao-/
 *
 * @author liwei
 * @date 2019/7/18 8:46 AM
 */
public class Solution3 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 为了让搜索范围更小，我们始终让 num1 是那个更短的数组，PPT 第 9 张
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        // 上述交换保证了 m <= n，在更短的区间 [0, m] 中搜索，会更快一些
        int m = nums1.length;
        int n = nums2.length;

        // 使用二分查找算法在数组 nums1 中搜索一个索引 i，PPT 第 9 张
        int left = 0;
        int right = m;
        // 这里使用的是最简单的、"传统"的二分查找法模板，使用"高级的"二分查找法模板在退出循环时候处理不方便
        while (left <= right) {
            // 尝试要找的索引，在区间里完成二分，为了保证语义，这里就不定义成 mid 了
            // 用加号和右移是安全的做法，即使在溢出的时候都能保证结果正确，但是 Python 中不存在溢出
            // 参考：https://leetcode-cn.com/problems/guess-number-higher-or-lower/solution/shi-fen-hao-yong-de-er-fen-cha-zhao-fa-mo-ban-pyth/
            int i = (left + right) >>> 1;
            // j 的取值在 PPT 第 7 张
            int j = ((m + n + 1) >>> 1) - i;

            // 边界值的特殊取法的原因在 PPT 第 10 张
            int nums1LeftMax = i == 0 ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = i == m ? Integer.MAX_VALUE : nums1[i];

            int nums2LeftMax = j == 0 ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = j == n ? Integer.MAX_VALUE : nums2[j];

            // 交叉小于等于关系成立，那么中位数就可以从"边界线"两边的数得到，原因在 PPT 第 2 张、第 3 张
            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // 已经找到解了，分数组之和是奇数还是偶数得到不同的结果，原因在 PPT 第 2 张
                if (((m + n) & 1) == 1) {
                    return Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return (double) ((Math.max(nums1LeftMax, nums2LeftMax) + Math.min(nums1RightMin, nums2RightMin))) / 2;
                }
            } else if (nums2LeftMax > nums1RightMin) {
                // 这个分支缩短边界的原因在 PPT 第 8 张
                left = i + 1;
            } else {
                // 这个分支缩短边界的原因在 PPT 第 8 张
                right = i - 1;
            }
        }
        throw new IllegalArgumentException("传入无效的参数，输入的数组不是有序数组，算法失效");
    }
}
