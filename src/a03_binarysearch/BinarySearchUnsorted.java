package a03_binarysearch;

import a02_corrector.Corrector;

import java.beans.beancontext.BeanContextServiceRevokedEvent;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class BinarySearchUnsorted {

    // 寻找峰值 i位置比i-1与i+1都大，便认为是峰值
    // 相邻两数不想等, 且一定存在峰值
    // 0位置只考虑其右边，n位置只考虑其左边
    // 返回任意峰值位置index

    public static int findPeak(Integer[] array) {
        int n = array.length;
        if (array.length == 1) {
            return 0;
        }
        if (array[0] > array[1]) {
            return 0;
        }
        if (array[n - 1] > array[n - 2]) {
            return n - 1;
        }
        //这块的left与right已经不从最两边的数开始了
        int ans = -1;
        int left = 1, right = n - 2;
        int middle = 0;
        while (left <= right) {
            middle = left + (right - left) / 2;
            if (array[middle] < array[middle - 1]) {
                right = middle - 1;
            } else if (array[middle] < array[middle + 1]) {
                left = middle + 1;
            } else {
                ans = middle;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Integer[] genArray = Corrector.genArray();
        int peak = findPeak(genArray);
        Corrector.printResult(genArray);
        System.out.println(genArray[peak]);
    }
}
