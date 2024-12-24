package com.example.sort;

import java.util.Comparator;

public class TimSort {
    private static final int RUN = 32;

    private static <T> void insertionSort(T[] array, int left, int right, Comparator<? super T> comparator) {
        for (int i = left + 1; i <= right; i++) {
            T temp = array[i];
            int j = i - 1;
            while (j >= left && comparator.compare(array[j], temp) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
    }

    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<? super T> comparator) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        T[] leftArray = (T[]) new Object[len1];
        T[] rightArray = (T[]) new Object[len2];

        System.arraycopy(array, left, leftArray, 0, len1);
        System.arraycopy(array, mid + 1, rightArray, 0, len2);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < len1) {
            array[k++] = leftArray[i++];
        }
        while (j < len2) {
            array[k++] = rightArray[j++];
        }
    }

    public static <T> void timSort(T[] array, Comparator<? super T> comparator) {
        int n = array.length;

        for (int i = 0; i < n; i += RUN) {
            insertionSort(array, i, Math.min(i + RUN - 1, n - 1), comparator);
        }

        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));
                if (mid < right) {
                    merge(array, left, mid, right, comparator);
                }
            }
        }
    }
}
