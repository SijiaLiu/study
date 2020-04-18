package com.lsj.tree;

public class TrieNode {

    public char val;
    public boolean endWord;
    public TrieNode[] child;
    public final static int DEFAULT_SIZE = 1000;


    public TrieNode() {
        child = new TrieNode[DEFAULT_SIZE];
    }

    public TrieNode(char val) {
        this.val = val;
        child = new TrieNode[DEFAULT_SIZE];
    }


}
