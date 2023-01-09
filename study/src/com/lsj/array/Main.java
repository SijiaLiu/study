package com.lsj.array;

public class Main {

    public static void main(String[] args) {
        MissingNumber missingNumber = new MissingNumber();


        int[] nums = new int[]{2, 3, 1, 0, 2, 5, 3};
        int firstDuplicateNum = missingNumber.findFirstDuplicateNum(nums);
        System.out.println(firstDuplicateNum);
    }
}
