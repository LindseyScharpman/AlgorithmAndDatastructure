package ch41_无向图;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

/**
 * 检测无向图G是否有环
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle = false;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];

        for (int v = 0; v < G.V(); ++v)
            if (!marked[v])
                dfs(G, v, v);
    }

    /**
     * @param g
     * @param v    dfs路径当前正在被访问的节点
     * @param preV dfs路径上v的前驱节点
     */
    private void dfs(Graph g, int v, int preV) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w, v);
            else if (w != preV)
                hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
