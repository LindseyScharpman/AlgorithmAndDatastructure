package ch14_算法分析;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//3-sum问题，假设整数互不相同
public class ThreeSum 
{	
	//(N^2)log(N)
	public static int count(int[] arr)
	{
		int cnt = 0;
		int len = arr.length;
		
		Arrays.sort(arr);
		for(int i = 0; i < len; ++i)
		{
			for(int j = i + 1; j < len; ++j)
			{
				if( BinarySearch.indexOf(arr, -(arr[i]+arr[j]) ) > j )
					++cnt;
			}
		}
		return cnt;
	}
	
	//暴力法解决3-sum问题
	public static int countBruce(int[] arr)
	{
		int len = arr.length;
		int cnt = 0;
		for(int i = 0; i < len; ++i)
		{
			for(int j = i + 1; j < len; ++j)
			{
				for(int k = j + 1; k < len; ++k)
				{
					if(arr[i] + arr[j] + arr[k] == 0)
						cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	public static void main(String[] args) 
	{
		int[] arr = new In(args[0]).readAllInts();
		StdOut.println( ThreeSum.count(arr) );
		StdOut.println( ThreeSum.countBruce(arr) );
	}

}
