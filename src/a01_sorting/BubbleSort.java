package a01_sorting;

import a02_corrector.Corrector;

/**
 * @author Chenyu Liu
 * @since 2/28/25 Friday
 **/

public class BubbleSort{

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
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        Corrector.printResult(array);
    }

    public static void main(String[] args) throws Exception {
        Integer[] genArray = Corrector.genArray();
        new BubbleSort().sort(genArray);
    }
}
