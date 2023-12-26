/*
 * All Rights Reserved.
 *
 */
package trie;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Trie.
 *
 * @author Carl, 2023-12-26 16:31
 */
public class Trie {
    private static Logger logger = LogManager.getLogger(Trie.class);

    public final static TrieNode wordsTree = new TrieNode();

    public void insert(String words) {
        TrieNode root = wordsTree;
        char[] chars = words.toCharArray();
        for (char c : chars) {
            int idx = c - 'a';
            if (root.slot[idx] == null) {
                root.slot[idx] = new TrieNode();
            }
            root = root.slot[idx];
            root.c = c;
            root.prefix++;
        }
        root.isWord = true;
    }

    public List<String> searchByPrefix(String prefix) {
        TrieNode root = wordsTree;
        char[] chars = prefix.toCharArray();
        StringBuilder cache = new StringBuilder();
        //根据前缀找到对应匹配的字符串
        for (char c : chars) {
            int idx = c - 'a';
            // 匹配为空
            if (idx > root.slot.length || idx < 0 || root.slot[idx] == null) {
                return new ArrayList<>();
            }
            cache.append(c);
            root = root.slot[idx];
        }
        if ("".equals(cache.toString())) {
            return new ArrayList<>();
        }
        // 模糊匹配：根据前缀的最后一个单词，递归遍历所有的单词
        ArrayList<String> list = new ArrayList<>();
        if (root.prefix != 0) {
            for (int i = 0; i < root.slot.length; i++) {
                if (root.slot[i] != null) {
                    char c = (char) (i + 'a');
                    collect(root.slot[i], String.valueOf(cache) + c, list, 15);
                    if (list.size() >= 15) {
                        return list;
                    }
                }
            }
        }
        return list;
    }

    protected void collect(TrieNode trieNode, String pre, List<String> queue, int resultLimit) {
        // 找到单词
        if (trieNode.isWord) {
            trieNode.word = pre;
            // 保存检索到的单词到 queue
            queue.add(trieNode.word);
            if (queue.size() >= resultLimit) {
                return;
            }
        }
        // 递归调用，查找单词
        for (int i = 0; i < trieNode.slot.length; i++) {
            char c = (char) ('a' + i);
            if (trieNode.slot[i] != null) {
                collect(trieNode.slot[i], pre + c, queue, resultLimit);
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("books");
        logger.info(trie.searchByPrefix("bok"));
    }

}
