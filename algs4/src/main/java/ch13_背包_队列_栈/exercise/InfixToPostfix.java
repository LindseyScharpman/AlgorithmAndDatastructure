package ch13_背包_队列_栈.exercise;


import ch13_背包_队列_栈.util.LinkedStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//中缀表达式转换为后缀表达式
//用2个栈实现，当遇到 ) 时,把操作数和操作符弹出组成一个新的操作数压入到操作数栈
public class InfixToPostfix {

    // input: ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
    // output: 1 2 + 3 4 - 5 6 - * *
    public static void main(String[] args) {
        LinkedStack<String> vals = new LinkedStack<String>(); // 操作数
        LinkedStack<String> ops = new LinkedStack<String>(); // 操作符

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("("))
                continue; // 忽略左括号
            else if (s.equals("+"))
                ops.push(s);
            else if (s.equals("-"))
                ops.push(s);
            else if (s.equals("*"))
                ops.push(s);
            else if (s.equals("/"))
                ops.push(s);
            else if (s.equals(")")) {
                String val1 = vals.pop();
                String val2 = vals.pop();
                String op = ops.pop();
                String exp = val2 + " " + val1 + " " + op + " ";
                vals.push(exp);
            } else
                vals.push(s);
        }

        StdOut.println(vals.pop());
    }
}
