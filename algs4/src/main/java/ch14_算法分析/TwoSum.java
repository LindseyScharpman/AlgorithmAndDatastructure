package ch14_算法分析;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

//2-sum问题，假设整数互不相同
public class TwoSum 
{

	@SuppressWarnings("deprecation")
	public static int count(int[] arr)
	{
		int cnt = 0;
		int len = arr.length;
		
		Arrays.sort(arr);
		for(int i = 0; i < len; ++i)
		{
			if( BinarySearch.rank( -arr[i], arr) > i )
			{
				cnt++;
			}
		}
		return cnt;
	}
	
	
	public static void main(String[] args) 
	{
		int[] arr = new In(args[0]).readAllInts();
		StdOut.println( TwoSum.count(arr) );
	}

}
