package ch13_背包_队列_栈.exercise;


import edu.princeton.cs.algs4.StdIn;

public class ex1_3_3 {
    /* 混合入栈出栈的判断
     * 核心思想:当成功弹出一个元素时,就把该位置标记为无效,然后把top指向新的栈顶的位置
     * 如果下一次要弹出的元素比arr[top]小,那么就肯定出问题了
     */
    public static void main(String[] args) {

        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int top = 0;
        while (!StdIn.isEmpty()) {
            int in = StdIn.readInt();
            if (arr[in] >= arr[top]) {
                arr[in] = -1;
                for (int i = in - 1; i >= 0; --i) {
                    if (arr[i] != -1) {
                        top = i;
                        break;
                    }
                }
            } else {
                System.out.println("invalid sequenece!");
                return;
            }
        }
        System.out.println("valid sequenece!");
    }
}
