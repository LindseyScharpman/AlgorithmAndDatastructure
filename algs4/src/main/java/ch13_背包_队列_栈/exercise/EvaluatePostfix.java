package ch13_背包_队列_栈.exercise;

import ch13_背包_队列_栈.util.LinkedStack;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//对后缀表达式求值
public class EvaluatePostfix {

    // input: 1 2 + 3 4 - 5 6 - * *
    // output: 3.0
    public static void main(String[] args) {
        LinkedStack<String> vals = new LinkedStack<String>(); // 操作数

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) {
                double val1 = Double.parseDouble(vals.pop());
                double val2 = Double.parseDouble(vals.pop());
                vals.push(val2 + val1 + "");
            } else if (s.equals("-")) {
                double val1 = Double.parseDouble(vals.pop());
                double val2 = Double.parseDouble(vals.pop());
                vals.push(val2 - val1 + "");
            } else if (s.equals("*")) {
                double val1 = Double.parseDouble(vals.pop());
                double val2 = Double.parseDouble(vals.pop());
                vals.push(val2 * val1 + "");
            } else if (s.equals("/")) {
                double val1 = Double.parseDouble(vals.pop());
                double val2 = Double.parseDouble(vals.pop());
                vals.push(val2 / val1 + "");
            } else
                vals.push(s);
        }

        StdOut.println(vals.pop());
    }
}
