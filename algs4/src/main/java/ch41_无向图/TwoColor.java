package ch41_无向图;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

/**
 * 检测该无向图是否为二分图
 */
public class TwoColor {
    private boolean[] marked;
    private boolean[] color;
    private boolean isTwoColorable = false;

    public TwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];

        for (int v = 0; v < G.V(); ++v)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                color[w] = !color[v];
                dfs(g, w);
            } else if (color[w] == color[v]) {
                isTwoColorable = false;
            }
        }
    }

    public boolean isBipartite() {
        return isTwoColorable;
    }
}
