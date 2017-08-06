package ch32_二叉查找树;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class ex3_2_38<Key extends Comparable<Key>, Value> {
    private Node root; // 二叉查找树的根节点
    int count = 0; // 用于二叉树检查
    private int x = 0;    //绘图用


    private class Node {
        private Key key; // 键
        private Value val; // 值
        private Node left, right; // 指向子树的链接
        private int N; // 以该节点为根节点的子树中的节点总数

        private int x, y; // 坐标,用于绘图用

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // 查找键为key的节点的值
    public Value get(Key key) {
        if (root == null || key == null)
            return null;
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    // 插入节点key-val
    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("put");
        if (val == null) {
            delete(key);
            return;
        }

        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 删除最小的键
    public void deleteMin() {
        if (isEmpty())
            throw new RuntimeException("deleteMin");

        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 删除最大的键
    public void delteMax() {
        if (isEmpty())
            throw new RuntimeException("delteMax");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return null;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("delete");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return x.left = delete(x.left, key);
        else if (cmp > 0)
            return x.right = delete(x.right, key);
        else {
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 返回BST最小的节点的键
    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("min");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    // 返回BST最大的节点的键
    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("max");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    // 小于等于key的最大键
    public Key floor(Key key) {
        if (key == null)
            throw new NullPointerException("floor");
        if (isEmpty())
            throw new NoSuchElementException("floor");
        Node x = floor(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node floor(Node x, Key key) {
        /*
         * 如果给定的键key等于BST的根节点的键,那么小于等于key的最大键一定是根节点. 如果给定的键key小于BST的根节点的键,那么小于等于key的最大键一定在根节点的左子树中.
		 * 如果给定的键key大于BST的根节点的键,那么只有当根节点的右子树中存在小于等于key的节点时,小于等于key的最大键才会出现在右子树中, 否则根节点就是小于等于key的最大键.
		 */
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        else if (cmp < 0)
            return floor(x.left, key);
        else {
            Node t = floor(x.right, key);
            if (t != null)
                return t;
            else
                return x;
        }
    }

    // 大于等于key的最小键
    public Key ceiling(Key key) {
        if (key == null)
            throw new NullPointerException("ceiling");
        if (isEmpty())
            throw new NoSuchElementException("ceiling");
        Node x = ceiling(root, key);
        if (x == null)
            return null;
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
		/*
		 * 如果给定的键key等于BST的根节点的键,那么大于等于key的最小键一定是根节点.
		 * 如果给定的键key大于BST的根节点的键,那么大于等于key的最小键一定在根节点的右子树中.
		 * 如果给定的键key小于BST的根节点的键,那么只有当根节点的左子树中存在大于等于key的节点时,大于等于key的最小键才会出现在右子树中, 否则根节点就是大于等于key的最小键.
		 */
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        else if (cmp > 0)
            return ceiling(x.right, key);
        else {
            Node t = ceiling(x.left, key);
            if (t != null)
                return t;
            else
                return x;
        }
    }

    // 查找排名为k的键 k=0,1,2,3...
    public Key select(int k) {
        if (k < 0 || k >= size())
            throw new IllegalArgumentException("select");
        Node x = select(root, k);
        if (x == null)
            return null;
        return x.key;
    }

    private Node select(Node x, int k) {
		/*
		 * 如果左子树中节点数t大于k,那么就继续递归地在左子树中查找排名为k的键. 如果t等于k,就返回根节点的键. 如果t小于k,就递归地在右子树中查找排名为k-t-1的键.
		 */
        if (x == null)
            return null;
        int t = size(x.left);
        if (t > k)
            return select(x.left, k);
        else if (t < k)
            return select(x.right, k - t - 1);
        else
            return x;
    }

    // 返回给定的键key的排名 排名值为0,1,2...
    public int rank(Key key) {
        if (key == null)
            throw new NullPointerException("rank");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(x.left, key);
        else if (cmp > 0)
            return rank(x.right, key) + size(x.left) + 1;
        else
            return size(x.left);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null || hi == null || (lo.compareTo(hi) > 0))
            throw new IllegalArgumentException("keys");

        List<Key> lists = new ArrayList<Key>();
        keys(root, lists, lo, hi);
        return lists;
    }

    private void keys(Node x, List<Key> lists, Key lo, Key hi) {
        if (x == null)
            return;
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0)
            keys(x.left, lists, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0)
            lists.add(x.key);
        if (cmpHi > 0)
            keys(x.right, lists, lo, hi);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // 计算BST的节点个数
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("contains");
        return get(key) != null;
    }

    // 层序遍历
    public Iterable<Key> levelOrder() {
        return levelOrder(root);
    }

    private Iterable<Key> levelOrder(Node x) {
        Queue<Node> q = new Queue<Node>();
        Queue<Key> keys = new Queue<Key>();
        q.enqueue(x);
        while (!q.isEmpty()) {
            Node t = q.dequeue();
            if (t == null)
                continue;
            keys.enqueue(t.key);
            q.enqueue(t.left);
            q.enqueue(t.right);
        }
        return keys;
    }

    // 练习
    // 3.2.6 计算数的高度
    public int heightR() {
        return heightR(root);
    }

    private int heightR(Node x) {
        if (x == null) // 高度等于递归的次数减去1
            return -1;
        // 递归的计算树的高度
        return 1 + Math.max(heightR(x.left), heightR(x.right));
    }

    // 3.2.13 非递归get方法
    public Value get2(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.val;
        }
        return null;
    }

    // 3.2.25 完美平衡
    // 用一组键构造一颗完美平衡的二叉树,仅仅处理了键,其余的属性均未处理
    public void createPerfectBST(Key keys[]) {
        root = createPerfectBST(root, 0, keys.length - 1, keys);
    }

    // 构建完美平衡的二叉树,前提keys升序
    // 核心思想是二叉树的根节点就是keys[mid],左子树指向keys[lo...mid-1],右子树指向keys[mid+1...hi]
    // 本质上就是二分查找
    private Node createPerfectBST(Node x, int lo, int hi, Key keys[]) {
        if (lo > hi)
            return null;
        if (x == null)
            x = new Node(null, null, 0);
        int mid = (lo + hi) / 2;
        x.key = keys[mid];
        x.left = createPerfectBST(x.left, lo, mid - 1, keys);
        x.right = createPerfectBST(x.right, mid + 1, hi, keys);
        return x;
    }

    // 3.2.29 二叉树检查
    public boolean isBinaryTree(Node x) {
        countNodes(x);
        System.out.println(count + ":" + size(x));
        return count == size(x);
    }

    private void countNodes(Node x) {
        if (x == null)
            return;
        countNodes(x.left);
        ++count;
        countNodes(x.right);
    }

    // 3.2.33 选择/排名检查
    public boolean isSelectRankConsistant() {
        int size = size();
        for (int i = 0; i < size; ++i)
            if (rank(select(i)) != i)
                return false;
        for (Key key : keys())
            if (key.compareTo(select(rank(key))) != 0)
                return false;
        return true;
    }

    // 3.2.38 绘图
    public void draw() {
        setXY(root, 0);

        Queue<Node> q = new Queue<Node>();
        Queue<Node> nodes = new Queue<Node>();
        q.enqueue(root);
        while (!q.isEmpty()) {
            Node t = q.dequeue();
            if (t == null)
                continue;
            nodes.enqueue(t);
            q.enqueue(t.left);
            q.enqueue(t.right);
        }

        //打印出来x,y坐标
        for (Node node : nodes)
            StdOut.println(node.key + ":" + node.x + ":" + node.y);
    }

    private void setXY(Node node, int y) {
        if (node == null)
            return;

        setXY(node.left, y + 1);
        ++x;
        node.x = x;
        node.y = y;
        setXY(node.right, y + 1);
    }

    public static void main(String[] args) {

        ex3_2_38<String, Integer> st = new ex3_2_38<>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        st.draw();
    }
}
