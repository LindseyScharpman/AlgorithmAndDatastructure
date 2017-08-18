package ch42_有向图;

/**
 * Created by WQS on 2017/8/8.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * 有向图的拓扑排序
 * 1： 检测是否是DAG
 * 2： 如果是DAG,则对其做DFS,其逆后序排序就是该有向图的拓扑排序
 */
public class Topological {

    /**
     * 有向图的拓扑排序
     */
    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle dc = new DirectedCycle(G);
        if (dc.hasCycle())
            order = null;
        else {
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            order = depthFirstOrder.reversePostOrder();
        }
    }

    /**
     * 取得有向图的拓扑排序,如果该有向图不是DAG则返回null
     *
     * @return
     */
    public Iterable<Integer> getOrder() {
        return order;
    }

    public static void main(String[] args) {
        In in = new In(new File("algs4/src/main/java/ch42_有向图/tinyDAG.txt"));
        Digraph g = new Digraph(in);

        Topological topo = new Topological(g);
        System.out.println(topo.getOrder());
    }
}
