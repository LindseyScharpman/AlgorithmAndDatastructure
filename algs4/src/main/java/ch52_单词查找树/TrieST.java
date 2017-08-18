package ch52_单词查找树;

/**
 * Created by WQS on 2017/8/15.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * R向单词查找树
 */
public class TrieST<Value> {
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    /**
     * 返回key对应的值
     *
     * @param key
     * @return
     */
    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x.val == null)
            return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length())
            return x;
        return get(x.next[key.charAt(d)], key, d + 1);
    }

    /**
     * 向表中插入键值对
     *
     * @param key
     * @param value
     */
    public void put(String key, Value value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, Value value, int d) {
        if (x == null)
            x = new Node();
        if (d == key.length()) {
            x.val = value;
            return x;
        }

        x.next[key.charAt(d)] = put(x.next[key.charAt(d)], key, value, d + 1);
        return x;
    }


    /**
     * 返回所有的键
     *
     * @return
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * 返回以prefix为前缀的键
     *
     * @param prefix
     * @return
     */
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> q = new ArrayDeque<>();
        Node prefixRoot = get(this.root, prefix, 0);
        collect(prefixRoot, prefix, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null)
            return;
        if (x.val != null)
            q.add(pre);
        for (char c = 0; c < R; ++c)
            collect(x.next[c], pre + c, q);
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new ArrayDeque<>();
        collect(this.root, "", pat, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) return;
        if (d == pat.length()) {
            if (x.val != null)
                q.add(pre);
            return;
        }

        for (char c = 0; c < R; ++c)
            if (pat.charAt(d) == '.' || pat.charAt(d) == c)
                collect(x.next[c], pre + c, pat, q);
    }

    /**
     * 返回s的前缀中最长的键
     *
     * @param s
     * @return
     */
    public String longestPrefixOf(String s) {
        int len = search(root, s, 0, 0);
        return s.substring(0, len);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null)
            return length;
        if (x.val != null)
            length = d;
        if (d == s.length())
            return length;
        return search(x.next[s.charAt(d)], s, d + 1, length);
    }

    public static void main(String[] args) {
        TrieST<Integer> trieST = new TrieST<>();

        trieST.put("she", 0);
        trieST.put("sells", 1);
        trieST.put("sea", 2);
        trieST.put("shells", 3);
        trieST.put("by", 4);
        trieST.put("the", 5);
        trieST.put("sea", 6);
        trieST.put("shore", 7);


        System.out.println(trieST.get("shore"));

        trieST.keys().forEach(System.out::println);

        System.out.println(trieST.longestPrefixOf("shellsort"));
    }
}
