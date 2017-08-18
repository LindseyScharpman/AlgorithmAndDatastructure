package ch41_无向图;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class BreadFirstPaths {
    private boolean[] marked;
    private int edgeTo[];
    private int start;

    public BreadFirstPaths(Graph G, int start) {
        this.start = start;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];

        marked[start] = true;
        bfs(G, start);
    }

    public void bfs(Graph G, int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> path(int v) {
        Stack<Integer> s = new Stack<>();
        if (!hasPathTo(v))
            return s;
        for (int x = v; x != start; x = edgeTo[x])
            s.push(x);
        s.push(start);
        return s;
    }

    public static void main(String[] args) {
        Graph g = new Graph(new In(new File("algs4/src/main/java/ch41_无向图/tinyCG.txt")));

        int start = 0;
        BreadFirstPaths p = new BreadFirstPaths(g, start);

        for (int v = 0; v < g.V(); ++v) {
            StdOut.print(start + " -> " + v + ":");
            if (p.hasPathTo(v))
                for (int x : p.path(v))
                    StdOut.print(x + "<--");
            StdOut.println();
        }
    }

}
