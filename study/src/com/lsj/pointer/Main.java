package com.lsj.pointer;


public class Main {
    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome("babad"));

        Palindrome palindrome = new Palindrome();
        System.out.println("验证回文串：" + palindrome.isPalindrome(".,"));
        System.out.println("验证回文数：" + palindrome.isPalindrome(0));
    }
}
