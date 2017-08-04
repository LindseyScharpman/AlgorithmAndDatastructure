package ch23_快速排序;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickOptimization
{
	public static void sort( Comparable[] a )
	{
		StdRandom.shuffle( a );
		sort( a, 0, a.length - 1 );
	}

	// 三向切分快速排序,升序
	// 时间：O(N)~O(NlogN),空间：logN
	public static void sort( Comparable[] a, int lo, int hi )
	{
		// 子数组元素较小时,采用插入排序,一般取5~15
		if( lo + 15 >= hi )
		{
			for( int i = lo + 1; i <= hi; ++i )
			{
				for( int j = i; j > lo && less( a[j], a[j-1] ); --j )
					swap( a, j-1, j );
			}
			return;
		}

		int lt = lo, gt = hi, i = lo + 1;
		Comparable key = a[lo];
		// 把a[lo]....a[hi]划分为a[lo...lt-1] < v = a[lt...gt] < a[gt+1...hi]
		while( i <= gt )
		{
			int cmp = a[i].compareTo( key );
			if( cmp < 0 )
				swap( a, i++, lt++ );
			else if( cmp > 0 )
				swap( a, i, gt-- );
			else
				++i;
		}
		sort( a, lo, lt - 1 );
		sort( a, gt + 1, hi );
	}

	// v < w 返回true,否则返回false
	private static boolean less( Comparable v, Comparable w )
	{
		return v.compareTo( w ) < 0;
	}

	// 交换a[i] a[j]
	private static void swap( Comparable[] a, int i, int j )
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// 打印数组a
	private static void show( Comparable[] a )
	{
		int len = a.length;
		for( int i = 0; i < len; ++i )
			StdOut.print( a[i] + " " );
		StdOut.println();
	}

	// 判断数组a是否为升序
	public static boolean isSorted( Comparable[] a )
	{
		int len = a.length;
		for( int i = 1; i < len; ++i )
			if( less( a[i], a[i - 1] ) )
				return false;
		return true;
	}

	public static void main( String[] args )
	{
		int N = 13;
		Integer[] a = new Integer[N];
		for( int i = 0; i < N; ++i )
			a[i] = StdRandom.uniform(100);

		sort( a );
		System.out.println( isSorted( a ) );
		show(a);
	}
}
