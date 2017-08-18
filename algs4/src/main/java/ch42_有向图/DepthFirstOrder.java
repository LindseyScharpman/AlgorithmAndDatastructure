package ch42_有向图;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import ch41_无向图.DepthFirstPaths;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 基于DFS的顶点排序
 */
public class DepthFirstOrder {

    private boolean[] marked;
    private Queue<Integer> pre = new ArrayDeque<>();        // 前序排列
    private Queue<Integer> post = new ArrayDeque<>();       // 后序排列
    private Stack<Integer> reversePost = new Stack<>();     //逆后序排列


    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); ++v)
            if (!marked[v])
                dfs(G, v);
    }

    private void dfs(Digraph g, int v) {
        pre.add(v);
        marked[v] = true;
        for (int w : g.adj(v)) {
            if (!marked[w])
                dfs(g, w);
        }

        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> preOrder() {
        return pre;
    }

    public Iterable<Integer> postOrder() {
        return post;
    }

    public Iterable<Integer> reversePostOrder() {
        return reversePost;
    }
}
