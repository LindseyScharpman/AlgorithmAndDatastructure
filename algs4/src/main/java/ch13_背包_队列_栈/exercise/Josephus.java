package ch13_背包_队列_栈.exercise;


import ch13_背包_队列_栈.util.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {

    public static void main(String[] args) {
        int N = 7; // 人数
        int M = 2; // 报到M的人死去,从1开始报数,若当前这个人死去,则后面的人从1开始重新报数

        LinkedQueue<Integer> q = new LinkedQueue<Integer>();

        for (int i = 0; i < N; ++i)
            q.enqueue(i);

        while (!q.isEmpty()) {
            for (int i = 0; i < M - 1; ++i)
                q.enqueue(q.dequeue());
            StdOut.println(q.dequeue() + " ");
        }
    }
}
