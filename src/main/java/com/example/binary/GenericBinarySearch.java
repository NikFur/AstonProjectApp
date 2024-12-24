package com.example.binary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class GenericBinarySearch<T> implements BinarySearch<T> {
    @Override
    public Optional<T> search(T[] items, T target, Comparator<T> comparator) {
        if (items == null || items.length == 0 || target == null || comparator == null) {
            return Optional.empty();
        }
        Arrays.sort(items, comparator);
        int index = Arrays.binarySearch(items, target, comparator);
        return (index >= 0) ? Optional.of(items[index]) : Optional.empty();
    }
}