package ch13_背包_队列_栈.exercise;


import ch13_背包_队列_栈.util.LinkedStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//练习1.3.9 补全中序表达式
public class CompleteInfix {

    // input: 1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )
    // output: ( ( 1 + 2 ) * ( ( 3 - 4 ) * ( 5 - 6 ) ) )
    // 用2个栈实现，当遇到 ) 时,把操作数和操作符弹出组成一个新的操作数压入到操作数栈
    // 如果想添加sqrt功能,则在push(exp)时需要注意sqrt只需要一个操作数
    public static void main(String[] args) {
        LinkedStack<String> vals = new LinkedStack<String>(); // 操作数
        LinkedStack<String> ops = new LinkedStack<String>(); // 操作符

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+"))
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
                String exp = " ( " + val2 + " " + op + " " + val1 + " ) ";
                vals.push(exp);
            } else
                vals.push(s);
        }
        StdOut.println(vals.pop());
    }
}
