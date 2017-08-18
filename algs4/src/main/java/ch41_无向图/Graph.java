package ch41_无向图;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class Graph {

    private int V;  //顶点数目
    private int E;  //边的数目
    private List<Integer>[] adj;    //邻接表

    public Graph(In in) {

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
        adj[w].add(v);
        E++;
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

    public static void main(String[] args) {
        System.out.println();
    }
}
