package com.lsj.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        SubSet subSet = new SubSet();
        System.out.println(subSet.subsets(new int[]{1, 2, 3}));

        Permutation permutation = new Permutation();
        System.out.println(Arrays.toString(permutation.permutation("qqqe")));
        System.out.println(permutation.subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(permutation.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));

        char[][] chars = new char[][] {
                {'c', 'a', 'a', 'g'},
                {'a', 'a', 'a', 's'},
                {'b', 'c', 'd', 'a'},
                {'b', 's', 'h', 'c'}};

        char[][] chars1 = new char[][] {
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {'a', 'a', 'a', 'b'}};

        HasPath hasPath = new HasPath();
        System.out.println(hasPath.hasPath("aaaaaaaaaaaaaaaaaaaa", chars1));

        ReadBinaryWatch readBinaryWatch = new ReadBinaryWatch();
        System.out.println(readBinaryWatch.readBinaryWatch(2));

        RobotMovingCount robotMovingCount = new RobotMovingCount();
        System.out.println(robotMovingCount.movingCount(1, 2, 1));

        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        List<List<Integer>> special = new ArrayList<>();
        List<Integer> s1 = new ArrayList<>();
        s1.add(3);
        s1.add(0);
        s1.add(5);
        List<Integer> s2 = new ArrayList<>();
        s2.add(1);
        s2.add(2);
        s2.add(10);
        special.add(s1);
        special.add(s2);
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);

        ShoppingOffers shoppingOffers = new ShoppingOffers();
        System.out.println(shoppingOffers.shoppingOffers(price, special, needs));

        System.out.println(Math.pow(2, 3));

        System.out.println(3 & 1);
        System.out.println(4 & 1);


        LetterCombinations letterCombinations = new LetterCombinations();
        System.out.println(letterCombinations.letterCombinations("23"));

        System.out.println(letterCombinations.combinationSum(new int[]{2, 3, 6, 7}, 7));

        System.out.println(Arrays.deepToString(letterCombinations.merge(new int[][]{{3, 7}, {2, 9}, {8, 10}, {15, 18}})));

        System.out.println(letterCombinations.lastRemaining(5, 3));

    }

}
