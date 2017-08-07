package ch33_平衡查找树;

import com.sun.glass.ui.Size;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by WQS on 2017/8/5.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    class Node {
        Key key;
        Value val;
        int N;  //子树的节点个数,包括自身这个节点
        Node left, right;
        boolean color;  // 指向该节点的那个节点的颜色

        public Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    private Node root;

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key, val, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0)
            h.left = put(h.left, key, val);
        else if (cmp > 0)
            h.right = put(h.right, key, val);
        else
            h.val = val;

        // 维持RB树的红黑性质
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
    }

    /**
     * TODO Hard to Understand.... T_T someday i will write it by myself....
     *
     * @param key
     */
    public void delete(Key key) {

    }

    /**
     * 返回红黑树的总共的节点个数
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    /**
     * 翻转颜色
     *
     * @param h
     */
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    /**
     * 右旋转操作,把左红链接翻转成右红链接
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    /**
     * 左旋转操作,把右红链接翻转成左红链接
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;

        x.color = h.color;
        h.color = RED;

        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }


    /**
     * 层序遍历
     */
    public void show() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node x = q.poll();
            System.out.println(x.key + ":" + x.val + ":" + x.color);
            if (x.left != null)
                q.add(x.left);
            if (x.right != null)
                q.add(x.right);
        }
    }

    public static void main(String[] args) {
        String[] d1 = new String[]{"S", "E", "A", "R", "C", "H", "X", "M", "P", "L"};
        String[] d2 = new String[]{"A", "C", "E", "H", "L", "M", "P", "R", "S", "X"};

        RedBlackBST<String, String> st1 = new RedBlackBST<>();
        RedBlackBST<String, String> st2 = new RedBlackBST<>();

        for (int i = 0; i < d1.length; ++i) {
            st1.put(d1[i], "" + i);
            st2.put(d2[i], "" + i);
        }


        st1.show();
        System.out.println("==================");
        st2.show();
    }
}

