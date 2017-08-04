package ch13_背包_队列_栈.exercise;

import ch13_背包_队列_栈.util.ResizingArrayQueue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class QueueApply {
    public static void main(String[] args) {
        ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
        int k = 3;
        // 读取标准输入的数据到队列q中
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            q.enqueue(item);
        }

        // 从倒数第0个开始数
        for (int i = 0; i < q.size() - k; ++i)
            q.dequeue();
        StdOut.println(q.dequeue());
    }
}