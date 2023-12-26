/*
 * All Rights Reserved.
 *
 */
package trie;


/**
 * TrieNode.
 * 
 * @author Carl, 2023-12-26 16:30
 */
public class TrieNode {
    /** 形成一个链 */
    public TrieNode[] slot = new TrieNode[26];

    /** 字母 */
    public char c;

    /** 单词：数量 > 0 表示一个单词 */
    public boolean isWord;

    /** 前缀 */
    public int prefix;

    /** 单词：具体的一个单词字符串 */
    public String word;

    public TrieNode[] getSlot() {
        return slot;
    }

    public void setSlot(TrieNode[] slot) {
        this.slot = slot;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public int getPrefix() {
        return prefix;
    }

    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
