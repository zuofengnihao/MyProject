package com.takozy;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        int[] nums = new int[] {34,7,3,87,21,67,32,8,332,554,781,-32,0,-11,-32,-48,-2,24};
        Test t = new Test();
        t.sortByBingGui(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortByBingGui(int[] nums) {
        fen(nums, 0, nums.length - 1);
    }
    
    public void fen (int[] nums, int begin, int end) {
        if (begin == end) return;
        int mid = (begin + end) / 2;
        fen(nums, begin, mid);
        fen(nums, mid + 1, end);
        he(nums, begin, mid, end);
    }
    
    public void he (int[] nums, int begin, int mid, int end) {
        int[] temp = nums.clone();
        int left = begin, right = mid + 1;
        while (left <= mid || right <= end) {
            if (left > mid) nums[begin++] = temp[right++];
            else if (right > end) nums[begin++] = temp[left++];
            else {
                if (temp[left] <= temp[right]) nums[begin++] = temp[left++];
                else nums[begin++] = temp[right++];
            }
        }
    }
}
