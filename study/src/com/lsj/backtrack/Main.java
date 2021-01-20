package com.lsj.backtrack;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        SubSet subSet = new SubSet();
        System.out.println(subSet.subsets(new int[]{1, 2, 3}));

        Permutation permutation = new Permutation();
        System.out.println(Arrays.toString(permutation.permutation("qqqe")));
    }

}
