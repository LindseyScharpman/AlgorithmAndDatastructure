package ch43_最小生成树;

/**
 * Created by WQS on 2017/8/9.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import edu.princeton.cs.algs4.In;

import java.util.LinkedList;
import java.util.List;

/**
 * 加权无向图
 */
public class EdgeWeightedGraph {

    private int V;
    private int E;
    private List<Edge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedGraph(In in) {
        this.V = in.readInt();
        adj = (List<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; ++v)
            adj[v] = new LinkedList<>();

        int e = in.readInt();
        for (int i = 0; i < e; ++i) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            Edge edge = new Edge(v, w, weight);
            addEdge(edge);
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        adj[v].add(edge);
        adj[w].add(edge);
        E++;
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }
}
