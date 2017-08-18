package ch42_有向图;

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class Digraph {
    private int V;
    private int E;
    private List<Integer>[] adj;

    public Digraph(int V) {

        this.V = V;
        this.E = 0;
        //noinspection unchecked
        adj = (List<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; ++v)
            adj[v] = new LinkedList<>();
    }


    public Digraph(In in) {

        this.V = in.readInt();
        int e = in.readInt();

        //noinspection unchecked
        adj = (List<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; ++v)
            adj[v] = new LinkedList<>();

        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        ++E;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    /**
     * 取得与顶点v相邻的所有顶点
     *
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; ++v)
            for (int w : adj(v))
                addEdge(w, v);
        return R;
    }
}
