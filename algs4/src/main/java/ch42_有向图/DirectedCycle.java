package ch42_有向图;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.Stack;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

/**
 * 检测有向图中是否存在环
 */
public class DirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onstack;
    private Stack<Integer> cycle;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        onstack = new boolean[G.V()];

        for (int v = 0; v < G.V(); ++v)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onstack[v] = true;

        for (int w : g.adj(v)) {
            if (hasCycle())
                return;
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onstack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
        onstack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch42_有向图/tinyDG.txt"));
        Digraph g = new Digraph(in);

        DirectedCycle dc = new DirectedCycle(g);
        System.out.println(dc.cycle());
    }
}
