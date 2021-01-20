package com.lsj.slidingwindow;

public class Main {

    public static void main(String[] args) {

        String S = "ABBBDOBECODEBANCh";
        String T = "ABC";
        MinWindowSub minWindowSub = new MinWindowSub();
        System.out.println(minWindowSub.minWindowSub(S, T));

        System.out.println(minWindowSub.checkInclusion("trinitrophenylmethylnitramine",
                "dinitrophenylhydrazinetrinitrophenylmethylnitramine"));
    }
}
