package com.lsj.pointer;

public class Palindrome {

    public boolean isPalindrome(String s) {
        // 全部置为小写字母
        s = s.toLowerCase();
        int length = s.length();
        // 单个字符或者空字符 是回文串
        if (length <= 1) {
            return true;
        }
        int left = 0;
        int right = length - 1;
        while (left < right) {
            // 不是小写字母的跳过
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            // 字符不相等 不是回文
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindrome(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int begin = 0;
        int end = chars.length - 1;
        while (begin < end) {
            if (chars[begin] != chars[end]) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }
}
