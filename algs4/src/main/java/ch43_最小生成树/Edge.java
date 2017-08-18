package ch43_最小生成树;

/**
 * Created by WQS on 2017/8/9.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */
public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new RuntimeException();
    }

    @Override
    public int compareTo(Edge that) {
        if (this.weight < that.weight)
            return -1;
        else if (this.weight > that.weight)
            return +1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
