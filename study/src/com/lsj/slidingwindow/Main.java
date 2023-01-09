package com.lsj.slidingwindow;

public class Main {

    public static void main(String[] args) {

        String S = "ABBBDOBECODEBANCh";
        String T = "ABC";
        MinWindowSub minWindowSub = new MinWindowSub();
        System.out.println(minWindowSub.minWindowSub(S, T));

        System.out.println(minWindowSub.checkInclusion("trinitrophenylmethylnitramine",
                "dinitrophenylhydrazinetrinitrophenylmethylnitramine"));

        int[] ints = {1, 1, 2};
        minWindowSub.removeDuplicates(ints);
        System.out.println(ints);
        System.out.println(minWindowSub.lengthOfLongestSubstringTwoDistinct("eceba"));
    }
}
