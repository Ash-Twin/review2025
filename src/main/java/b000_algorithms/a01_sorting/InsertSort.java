package b000_algorithms.a01_sorting;

import b000_algorithms.a02_corrector.Corrector;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class InsertSort {

    public static void swap(Integer[] arr, int firstIndex, int secondIndex) {
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }

    public void sort(Integer[] array) throws Exception {
        Corrector.printResult(array);
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j >= 0; j--) {
                if (array[j] < array[i]) {
                    swap(array, i, j);
                }
            }
        }
        Corrector.printResult(array);
    }

    public static void main(String[] args) throws Exception {
        new BubbleSort().sort(Corrector.genArray());
    }
}
