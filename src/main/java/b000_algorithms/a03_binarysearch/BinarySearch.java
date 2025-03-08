package main.java.b000_algorithms.a03_binarysearch;

import main.java.b000_algorithms.a02_corrector.Corrector;

import java.util.Arrays;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class BinarySearch {
    /**
     * 对于有序数组，找里面的某一个数
     */
    public static int find(Integer[] array, int target) {
        if (array == null || array.length < 1) {
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (leftIndex <= rightIndex) {
            //每次找左索引 + （右索引 - 左索引）/ 2 当作中点，相当于每次找左右索引的中点
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array[middleIndex] == target) {
                return middleIndex;
            } else if (array[middleIndex] > target) {
                rightIndex = middleIndex - 1;
            } else {
                leftIndex = middleIndex + 1;
            }
        }
        return -1;
    }

    public static int find(Integer[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int middleIndex = left + (right - left) / 2;
        if (array[middleIndex] == target) {
            return middleIndex;
        } else if (array[middleIndex] > target) {
            return find(array, left, middleIndex - 1, target);
        } else {
            return find(array, middleIndex + 1, right, target);
        }
    }

    public static int findRecur(Integer[] array, int target) {
        return find(array, 0, array.length - 1, target);
    }

    // find first greater than or equals target index
    public static int findLeftGTE(Integer[] array, int target) {
        int ans = -1;
        if (array == null || array.length < 1) {
            return ans;
        }
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (leftIndex <= rightIndex) {
            //每次找左索引 + （右索引 - 左索引）/ 2 当作中点，相当于每次找左右索引的中点
            //int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            //int middleIndex = （leftIndex + rightIndex) / 2;  //这种写法会出现内存溢出风险，当数组特别大的时候
            int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1); // 使用位运算也可以这么写
            if (array[middleIndex] >= target) {
                ans = middleIndex;
                rightIndex = middleIndex - 1;
            } else {
                leftIndex = middleIndex + 1;
            }
        }
        return ans;
    }

    public static int findRightLTE(Integer[] array, int target) {
        int ans = -1;
        if (array == null || array.length < 1) {
            return ans;
        }
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (leftIndex <= rightIndex) {
            //每次找左索引 + （右索引 - 左索引）/ 2 当作中点，相当于每次找左右索引的中点
            int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
            if (array[middleIndex] <= target) {
                ans = middleIndex;
                leftIndex = middleIndex + 1;
            } else {
                rightIndex = middleIndex - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Integer[] genArray = Corrector.genArray();
        Arrays.sort(genArray);
        Corrector.printResult(genArray);
        System.out.println(findRecur(genArray, genArray[7]));
        System.out.println(findLeftGTE(genArray, 5));
        System.out.println(genArray[findLeftGTE(genArray, 5)]);
        System.out.println(findRightLTE(genArray, 5));
        System.out.println(genArray[findRightLTE(genArray, 5)]);
    }
}
