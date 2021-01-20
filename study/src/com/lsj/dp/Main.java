package com.lsj.dp;

public class Main {
    public static void main(String[] args) {
        UniquePaths uniquePaths = new UniquePaths();
        System.out.println(uniquePaths.uniquePaths(7, 3));

        System.out.println(uniquePaths.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}}));

        MaxProduct maxProduct = new MaxProduct();
        System.out.println(maxProduct.maxProduct(new int[]{2, 3, -2, 4}));

        Stock stock = new Stock();
        System.out.println("买卖股票 只能买一次，最大利润：" + stock.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));

        System.out.println(stock.maxProfit(new int[]{1, 2, 3, 4, 5}, 1));


        CoinChange coinChange = new CoinChange();
        System.out.println("零钱兑换的所有可能兑换路径数 ：" + coinChange.changePath(7, new int[]{1, 2, 5}));
        System.out.println("零钱兑换的最少次数 ：" + coinChange.minChangeCount(11, new int[]{1, 2, 5}));

        EditDistance editDistance = new EditDistance();
        System.out.println("两个单词替换的最小操作数为：" + editDistance.minDistance("horse", "ros"));

        BackPack backPack = new BackPack();
        System.out.println("01背包问题 最大价值：" + backPack.backpack01(new int[]{3, 2, 1}, new int[]{4, 2, 3}, 5));
        System.out.println("零钱兑换的可能数：" + backPack.coinChange(new int[]{1, 2, 5}, 7));
        System.out.println("分割等和子集：" + backPack.slidingArray(new int[]{1, 2, 5}));
        System.out.println("分割等和子集：" + backPack.slidingArray02(new int[]{1, 2, 5}));

        ThrowEggs throwEggs = new ThrowEggs();
        System.out.println("高楼扔鸡蛋问题" + throwEggs.throwEggs(2, 100));

        LongestCommonSubsequence longestCommonSubsequence = new LongestCommonSubsequence();
        System.out.println("字符串的最长公共子序列长度 : " + longestCommonSubsequence.longestCommonSubsequence("abcde", "ace"));
        System.out.println("最长且连续的的递增序列长度 : " + longestCommonSubsequence.findLengthOfLCIS(new int[]{2,2,2,2}));
        System.out.println("最长回文子序列的长度 : " + longestCommonSubsequence.longestPalindromeSubseq("bbabc"));

        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println("最长回文串 : " + longestPalindrome.longestPalindrome("abbad"));

        longestPalindrome.traverseNoCopy(new int[][]{{1,2,3}, {4,5,6}, {7,8,9}});

        Kmp kmp = new Kmp("abc");
        System.out.println("KMP search : " + kmp.search("dsjjafbcgfabfdabcjk", "abc"));
        System.out.println("KMP search2 : " + kmp.searchKmp("abc", "ab"));
        kmp.divingBoard(2 ,1118596, 979);
        kmp.eraseOverlapIntervals(new int[][]{{1, 100}, {11, 22}, {1, 11}, {2, 12}});
    }
}
