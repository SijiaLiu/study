package com.lsj.tree;

public class Trie {

    public TrieNode root;

    public Trie() {
        root = new TrieNode(' ');
    }




    public void insert(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (trieNode.child[c - 'A'] == null) {
                trieNode.child[c - 'A'] = new TrieNode(c);
            }
            trieNode = trieNode.child[c - 'A'];
        }
        trieNode.endWord = true;
    }

    public boolean search(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (trieNode.child[word.charAt(i) - 'A'] == null) {
                return false;
            } else {
                trieNode = trieNode.child[word.charAt(i) - 'A'];
            }
        }
        return trieNode.endWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode trieNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (trieNode.child[prefix.charAt(i) - 'A'] == null) {
                return false;
            } else {
                trieNode = trieNode.child[prefix.charAt(i) - 'A'];
            }
        }
        return true;
    }
}
