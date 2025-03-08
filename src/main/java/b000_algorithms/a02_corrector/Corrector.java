package main.java.b000_algorithms.a02_corrector;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class Corrector {
    public static Integer[] genArray() {
        return new Random()
                .ints(20, -100, 101)  // 生成20个介于[-100, 100]的整数
                .boxed()               // 将int转为Integer
                .toArray(Integer[]::new);
    }

    public static Integer[] copyArray(Integer[] original) {
        // 创建一个与原数组长度相同的新数组
        Integer[] copy = new Integer[original.length];
        // 遍历原数组，将每个元素复制到新数组中
        System.arraycopy(original, 0, copy, 0, original.length);
        return copy;
    }

    public static boolean sameArray(Integer[] arr1, Integer[] arr2) {
        // 首先检查两个数组的引用是否相同
        if (arr1 == arr2) {
            return true;
        }
        // 检查两个数组是否都为null
        if (arr1 == null || arr2 == null) {
            return false;
        }
        // 检查两个数组的长度是否相同
        if (arr1.length != arr2.length) {
            return false;
        }
        // 遍历数组，比较每个元素
        for (int i = 0; i < arr1.length; i++) {
            if (!arr1[i].equals(arr2[i])) {
                return false;
            }
        }
        return true;
    }

    public static String sorted(Integer[] arr) {
        if (arr == null || arr.length <= 1) {
            return "asc"; // 空数组或只有一个元素的数组视为升序
        }
        boolean isAscending = true;
        boolean isDescending = true;
        // 遍历数组，检查是否升序
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                isAscending = false;
                break;
            }
        }
        // 遍历数组，检查是否降序
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                isDescending = false;
                break;
            }
        }
        if (isAscending) {
            return " asc";
        } else if (isDescending) {
            return " desc";
        } else {
            return " unsorted";
        }
    }

    public static void printResult(Integer[] array){
        // java 8 lambda
        System.out.println(
                Arrays.stream(array)
                        .map(Object::toString)
                        .collect(Collectors.joining(",", "[", "]"))
                        .concat(sorted(array)))
        ;
    }
}
