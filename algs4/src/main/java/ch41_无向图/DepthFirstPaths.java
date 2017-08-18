package ch41_无向图;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.Stack;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class DepthFirstPaths {

    private boolean[] marked;   //这个顶点是否调用过dfs

    /**
     * edgeTo[v] = x表示从start到v这个顶点的路径上,v的上一个顶点是x
     */
    private int[] edgeTo;

    private int start;         //起点

    public DepthFirstPaths(Graph g, int start) {
        this.start = start;
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g, start);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * 获取start到v的路径
     *
     * @param v
     * @return
     */
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
        DepthFirstPaths p = new DepthFirstPaths(g, start);

        for (int v = 0; v < g.V(); ++v) {
            StdOut.print(start + " -> " + v + ":");
            if (p.hasPathTo(v))
                for (int x : p.path(v))
                    StdOut.print(x + "-");
            StdOut.println();
        }
    }
}
