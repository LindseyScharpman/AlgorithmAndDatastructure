package ch14_算法分析.ex;

import java.util.Arrays;

public class ex1_4_17 {

	/* 找到一维数组最接近的一对
	 * 先排序,然后计算相邻元素差值,同时记录相邻元素
	 */
	public static void main(String[] args) 
	{
		double[] a = {1.0,2.0,2.9};
		Arrays.sort(a);
		double numA = a[0], numB = a[1];
		double diff = Math.abs(numA - numB);
		for(int i = 2; i < a.length; ++i)
		{
			if( a[i] - a[i-1] < diff )
			{
				diff = a[i] - a[i-1];
				numA = a[i-1];
				numB = a[i];
			}
		}
		System.out.println(diff + "\t" + numA + "\t" + numB);
	}

}
