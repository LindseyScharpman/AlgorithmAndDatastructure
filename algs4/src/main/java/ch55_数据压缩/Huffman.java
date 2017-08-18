package ch55_数据压缩;

import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by WQS on 2017/8/14.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class Huffman {

    private static int R = 256; //ASCII表大小

    public static String compress(byte[] input) throws IOException {
        int[] freq = new int[R];

        // 统计频率
        for (int i = 0; i < input.length; ++i)
            ++freq[input[i]];

        // 构造Huffman树
        Node root = buildHuffmanTree(freq);

        // 构造编译表
        String[] st = new String[R];
        buildCode(st, root, "");

        // 打印编解码用的单词树(便于调试,使用字符串来表示,实际中应该用位来表示)
        StringBuilder sb = new StringBuilder();
        writeTrie(root, sb);
        // 添加要被压缩的字节数组的长度.由于要用字符串表示,这里直接写死,假设长度最多用10位表示
        sb.append(String.format("%010d", input.length));

        // 使用Huffman编码处理输入
        for (int i = 0; i < input.length; ++i) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); ++j)
                if (code.charAt(j) == '1')
                    sb.append("1");
                else
                    sb.append("0");
        }

        return sb.toString();
    }


    private static Node buildHuffmanTree(int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int c = 0; c < R; ++c)
            if (freq[c] != 0)
                pq.add(new Node(c, freq[c], null, null));

        while (pq.size() > 1) {
            Node n1 = pq.remove();
            Node n2 = pq.remove();
            Node n = new Node(-1, n1.freq + n2.freq, n1, n2);
            pq.add(n);
        }
        return pq.remove(); //返回根节点
    }

    private static void buildCode(String[] st, Node x, String s) {
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + "0");
        buildCode(st, x.right, s + "1");
    }


    // 构造编解码用的单词树,本应该是二进制的0101...,这里便于调试,保存成字符串的0101...
    private static void writeTrie(Node x, StringBuilder sb) {
        if (x.isLeaf()) {
            sb.append("1");
            // 假设x.ch都在0-255范围内
            sb.append(String.format("%8s", Integer.toBinaryString(x.ch)).replace(' ', '0'));
            return;
        }
        sb.append("0");
        writeTrie(x.left, sb);
        writeTrie(x.right, sb);
    }


    // 由输入的二进制字符串解压成原来的字节数组(准确的说,该字符串的长度部分不是二进制字符串,其他部分都是二进制字符串)
    public static String expand(String s) {

        String[] fake = new String[]{s};
        Node root = readTrie(fake);

        int N = Integer.parseInt(fake[0].substring(0, 10));
        fake[0] = fake[0].substring(10);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            Node x = root;
            while (!x.isLeaf()) {
                if (fake[0].charAt(0) == '1')
                    x = x.right;
                else
                    x = x.left;
                fake[0] = fake[0].substring(1);
            }
            sb.append(Character.valueOf((char) x.ch));
        }

        return sb.toString();
    }

    private static Node readTrie(String[] fake) {
        boolean leaf = fake[0].charAt(0) == '1';
        fake[0] = fake[0].substring(1);
        if (leaf) {
            Node x = new Node(
                    Integer.parseInt(fake[0].substring(0, 8), 2),
                    0, null, null);
            fake[0] = fake[0].substring(8);
            return x;
        }
        return new Node(-1, 0, readTrie(fake), readTrie(fake));
    }

    public static void main(String[] args) throws IOException {

        System.out.println(String.format("%010d", 2));

        String s = "ABRACADABRA!";
        System.out.println(s);
        byte[] input = s.getBytes();

        String encodeOut = compress(input);
        System.out.println(encodeOut);


        String decodeOut = expand(encodeOut);
        System.out.println(decodeOut);

    }

    private static class Node implements Comparable<Node> {
        private int ch; // -1表示无效的字符,有效范围为0-255
        private int freq;
        private Node left, right;

        public Node(int ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        @Override
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }
}
