package ch13_背包_队列_栈.exercise;

import ch13_背包_队列_栈.util.LinkedStack;
import edu.princeton.cs.algs4.StdOut;

//练习1.3.4 检测括号是否配对完整
public class Parentheses {
    public static boolean isBalanced(String str) {
        LinkedStack<Character> s = new LinkedStack<Character>();
        int len = str.length();

        for (int i = 0; i < len; ++i) {
            char ch = str.charAt(i);

            // 左括号,压入栈
            if (ch == '[' || ch == '{' || ch == '(') {
                s.push(ch);
            }

            // 右括号,弹一个栈中的元素.如果不匹配,括号肯定配对不完整
            if (ch == ']' || ch == '}' || ch == ')') {
                // 如果第一个右括号之前没有左括号,则不匹配
                if (s.isEmpty())
                    return false;

                if (ch == ']' && (s.pop() != '['))
                    return false;
                if (ch == '}' && (s.pop() != '{'))
                    return false;
                if (ch == ')' && (s.pop() != '('))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "[()]{}{[()()]()}";
        StdOut.println(isBalanced(s));

        String s1 = "][]";
        StdOut.println(isBalanced(s1));
    }
}