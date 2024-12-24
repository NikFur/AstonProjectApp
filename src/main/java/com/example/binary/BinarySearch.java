package com.example.binary;

import java.util.Comparator;
import java.util.Optional;

public interface BinarySearch<T> {
    Optional<T> search(T[] items, T target, Comparator<T> comparator);
}
