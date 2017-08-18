package ch42_有向图;

import edu.princeton.cs.algs4.In;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class DirectedDFS {

    private boolean[] marked;


    /**
     * 从start节点出发,能到达哪些节点
     *
     * @param G
     * @param start
     */
    public DirectedDFS(Digraph G, int start) {
        marked = new boolean[G.V()];
        dfs(G, start);
    }

    /**
     * 从sources里面的节点出发,能到达哪些节点
     *
     * @param G
     * @param sources
     */
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v])
                dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w);
        }
    }

    /**
     * 节点v是否可以从start或者sources到达,start或者sources的值来源于构造器
     *
     * @param v
     * @return
     */
    public boolean marked(int v) {
        return marked[v];
    }


    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch42_有向图/tinyDG.txt"));
        Digraph g = new Digraph(in);

        List<Integer> sources = new LinkedList<>();
        sources.add(1);
        sources.add(2);
        sources.add(6);

        DirectedDFS dfs = new DirectedDFS(g, sources);
        for (int v = 0; v < g.V(); ++v) {
            if (dfs.marked(v))
                System.out.print(v + "\t");
        }
        System.out.println();
    }
}
