package ch34_散列表;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;


public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 16;

    /**
     * 键值对数量
     */
    private int N;

    /**
     * 线性探测表的大小
     */
    private int M;

    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public LinearProbingHashST(int size) {
        keys = (Key[]) new Object[size];
        vals = (Value[]) new Object[size];
        this.M = size;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("get");

        int h = hash(key);
        for (int i = h; keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null)
            throw new NullPointerException("put");
        if (val == null) {
            delete(key);
            return;
        }

        if (N * 2 >= M)
            resize(M * 2);

        //找到key所在的位置,如果找到,更新值,否则插入新的键值对.
        int i = 0;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        ++N;
//		printKeysVals();
    }

    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("ch11");
        if (!contains(key))
            return;

        //找到key所在的位置,并把它设置为null
        int i = hash(key);
        while (!keys[i].equals(key))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;

        //把key所在位置之后的键(到键值对为null时结束)重新插入到散列表中
        //重新插入某个键之前一定要把该键值对设置为null
        //由于需要重新put,put里面会设置N加1,因此需要让N减1.
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyRehash = keys[i];
            Value valRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            --N;
            put(keyRehash, valRehash);
            i = (i + 1) % M;
        }
        --N;
        if (N > 0 && N <= M / 8)
            resize(M / 2);
//		StdOut.print("D:");
//		printKeysVals();
    }

    private void resize(int size) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(size);
        for (int i = 0; i < M; ++i)
            if (keys[i] != null)
                temp.put(keys[i], vals[i]);
        keys = temp.keys;
        vals = temp.vals;
        M = temp.M;
    }

    public Iterable<Key> keys() {
        List<Key> result = new ArrayList<Key>();
        for (int i = 0; i < M; ++i)
            if (keys[i] != null)
                result.add(keys[i]);
        return result;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null)
            throw new NullPointerException("contains");
        return get(key) != null;
    }

    public void printKeysVals() {
        int len = keys.length;
        System.out.print("KEYVAL:");
        for (int i = 0; i < len; ++i) {
            System.out.print(((keys[i] == null) ? "?" : keys[i]) + ":" + ((vals[i] == null) ? "?" : vals[i]) + " ");
        }
        System.out.println("");
    }


    public static void main(String[] args) {

        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();
        String[] ks = new String[]{"ABC", "ACB", "BAC", "CBA", "CAB", "AB", "BA", "MN", "NM"};
        for (int i = 0; i < ks.length; ++i) {
            st.hash(ks[i]);
            st.put(ks[i], i);
        }

        st.delete("ABC");
        StdOut.println("get F" + st.get("CBA"));
        StdOut.println("-------------------");
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
