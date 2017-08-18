package ch60;

/**
 * Created by WQS on 2017/8/15.
 * Mail: 1027738387@qq.com
 * Github: https://github.com/wannibar
 */

import edu.princeton.cs.algs4.In;

import java.io.File;

/**
 * 最长重复子字符串
 */
public class LRS {

    public static void main(String[] args) {
        String text = "it was the best of times it was the";    //it was the
        text = "aacaagtttacaagc";   //acaag
        SuffixArray suffixArray = new SuffixArray(text);
        String lcs = "";
        for (int i = 1; i < text.length(); ++i) {
            int len = suffixArray.lcp(i);
            if (len > lcs.length())
                lcs = suffixArray.select(i).substring(0, len);
        }

        System.out.println(lcs);
    }
}
