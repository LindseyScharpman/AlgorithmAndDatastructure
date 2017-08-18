package ch43_最小生成树;

import ch42_有向图.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by WQS on 2017/8/9.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class LazyPrimMST {
    private boolean[] marked;
    private PriorityQueue<Edge> pq;
    private Queue<Edge> mst;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        pq = new PriorityQueue<>();
        mst = new ArrayDeque<>();

        visit(G, 0);

        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w])
                continue;
            mst.add(e);
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph g, int w) {
        marked[w] = true;
        for (Edge e : g.adj(w)) {
            if (!marked[e.other(w)])
                pq.add(e);
        }
    }

    public Iterable<Edge> getMstEdges() {
        return mst;
    }

    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch43_最小生成树/tinyEWG.txt"));
        EdgeWeightedGraph g = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(g);
        mst.getMstEdges().forEach(StdOut::println);

    }
}
