package main.java.b000_algorithms.a01_sorting;

import main.java.b000_algorithms.a02_corrector.Corrector;

/**
 * @author Chenyu Liu
 * @since 2/27/25 Thursday
 **/

public class SelectSort{

    private final Corrector corrector = new Corrector();

    public static void swap(Integer[] arr, int firstIndex, int secondIndex) {
        int tmp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = tmp;
    }

    public void sort(Integer[] array) throws Exception {

        corrector.printResult(array);
        if (array == null || array.length < 2) {
            return;
        }
        for (int minIndex, i = 0; i < array.length - 1; i++) {
            minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[minIndex] > array[j]) {
                    swap(array, minIndex, j);
                }
            }
        }
        corrector.printResult(array);
    }

    public static void main(String[] args) throws Exception {
        Integer[] genArray = Corrector.genArray();
        new SelectSort().sort(genArray);
    }
}
