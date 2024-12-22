package com.example;

import java.util.Arrays;

public class MyArray<T> {

    private final Object[] object_array;   //object array
    public final int len;

    // class constructor
    public MyArray(int len) {
        // instantiate a new Object array of specified length
        object_array = new Object[len];
        this.len = len;
    }

    // getter method
    public T getArr(int i) {
        @SuppressWarnings("unchecked") final T t = (T) object_array[i];
        return t;
    }

    // setter method

    public void setArr(int i, T t) {
        object_array[i] = t;
    }

    @Override
    public String toString() {
        return Arrays.toString(object_array);
    }
}


//    private T[] arr;
//
//    public MyArray(Class<T> clazz, int capacity) {
//        arr = (T[]) Array.newInstance(clazz, capacity);
//    }
//
//    public T[] getArr() {
//        return arr;
//    }
//
//    public void setArr(T[] arr) {
//        this.arr = arr;
//    }
//}
