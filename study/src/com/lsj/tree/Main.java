package com.lsj.tree;

public class Main {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
        TrieTest trieTest = new TrieTest();
        System.out.println(trieTest.isContainWord("SEE", grid));
        System.out.println(trieTest.findWords(new String[]{"ABC", "CSE", "DFC", "ADFHA", "LIU"}, grid));
    }
}
