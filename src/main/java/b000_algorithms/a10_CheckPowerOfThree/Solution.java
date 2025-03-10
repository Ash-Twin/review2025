package b000_algorithms.a10_CheckPowerOfThree;

/**
 * @author Chenyu Liu
 * @since 3/5/25 Wednesday
 **/

class Solution {
    /**
     * 判断一个数是否能表示为若干个不同的 3 的幂之和，可利用三进制的特性。在三进制中，每一位的权重分别是
     * 3
     * 0
     *  ,3
     * 1
     *  ,3
     * 2
     *  ,⋯
     * 。如果一个数可以表示为若干个不同的 3 的幂之和，那么它的三进制表示中每一位只能是 0 或者 1。因为如果某一位是 2，就意味着使用了两个相同的 3 的幂相加，不满足 “不同的 3 的幂之和” 这个条件。
     * @param n
     * @return
     */
    public boolean checkPowersOfThree(int n) {
        // 进入循环，只要 n 大于 0 就继续检查
        while (n > 0) {
            // 检查 n 对 3 取余的结果
            if (n % 3 == 2) {
                // 如果余数为 2，说明 n 的三进制表示中某一位是 2
                // 不满足“不同的 3 的幂之和”的条件，返回 false
                return false;
            }
            // 将 n 除以 3，相当于将 n 的三进制表示右移一位
            // 这样可以依次检查每一位
            n /= 3;
        }
        // 如果循环结束都没有发现余数为 2 的情况
        // 说明 n 的三进制表示中每一位都是 0 或者 1
        // 满足“不同的 3 的幂之和”的条件，返回 true
        return true;
    }
}