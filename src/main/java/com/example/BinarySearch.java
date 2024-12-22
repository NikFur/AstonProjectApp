package com.example;

import java.util.List;

public class BinarySearch<T extends Comparable<T>> {
    public int search(T[] array, T key) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            T result = array[mid];
            if (result.compareTo(key) < 0) {
                left = mid + 1;
            } else if (result.compareTo(key) > 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
