package com.takozy.guibingpaixun;

import java.util.Arrays;

public class Sort {

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] arr = new int[]{5,4,2};
        sort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr) {
        method(arr, 0, arr.length-1);
    }

    public void method (int[] arr, int begin, int end) {
        if (begin == end) {
            return;
        }
        if (begin == end - 1 && arr[begin] > arr[end]) {
            int temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
        } else {
            method(arr, 0, end / 2);
            method(arr, end / 2 + 1, end);
        }
    }
}
