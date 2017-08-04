package ch14_算法分析.ex;

import java.util.Arrays;

public class ex1_4_16 {

	/* 线性时间找到一维数组最遥远的一对
	 * 直接找到最大值和最小值即可
	 */
	public static void main(String[] args) 
	{
		double[] a = {1.0, 2.0, 2.9, 3.3, -1};
		double max = 0, min = 0;
		
		if( a.length%2 != 0 )
		{
			max = min = a[0];
			for(int i = 1; i < a.length; i+= 2)
			{
				if(a[i] > a[i+1])
				{
					if(a[i] > max)
						max = a[i];
					if(a[i+1] < min)
						min = a[i+1];
				}
				else
				{
					if(a[i+1] > max)
						max = a[i+1];
					if(a[i] < min)
						min = a[i];
				}
			}
		}
		else
		{
			max = min = a[0];
			for(int i = 0; i < a.length; i+= 2)
			{
				if(a[i] > a[i+1])
				{
					if(a[i] > max)
						max = a[i];
					if(a[i+1] < min)
						min = a[i+1];
				}
				else
				{
					if(a[i+1] > max)
						max = a[i+1];
					if(a[i] < min)
						min = a[i];
				}
			}
		}
		System.out.println(max + "\t" + min);
	}

}
