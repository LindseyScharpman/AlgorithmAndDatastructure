package ch25_应用;

public class Select
{
	// 在数组a中找到第k小的元素,k=0,1,2,3...
	public static Comparable select( Comparable[] a, int k )
	{
		int lo = 0, hi = a.length - 1;
		if( k > hi || k < lo )
			throw new RuntimeException( "select k is bigger or smaller !" );
		while( lo <= hi )
		{
			int j = partition( a, lo, hi );
			if( j == k )
				return a[k];
			else if( j > k )
				hi = j - 1;
			else
				lo = j + 1;
		}
		return a[k];
	}

	// 在数组a中找到第k小的元素,k=0,1,2,3...
	public static Comparable selectR( Comparable[] a, int k )
	{
		int lo = 0, hi = a.length - 1;
		if( k > hi || k < lo )
			throw new RuntimeException( "select k is bigger or smaller !" );
		return selectRhelper( a, k, 0, a.length - 1 );
	}

	private static Comparable selectRhelper( Comparable[] a, int k, int lo, int hi )
	{
		int j = partition( a, lo, hi );
		if( j == k )
			return a[k];
		else if( j > k )
			return selectRhelper( a, k, lo, j - 1 );
		else
			return selectRhelper( a, k, j + 1, hi );
	}

	private static int partition( Comparable a[], int lo, int hi )
	{
		// 以key = a[lo]为参考,a的左边都小于key,右边都大于等于key
		int keyIndex = lo;
		Comparable key = a[keyIndex];

		for( int i = lo + 1; i <= hi; ++i )
		{
			if( less( a[i], key ) )
				swap( a, ++keyIndex, i );
		}
		// 把key放在正确的位置上
		swap( a, lo, keyIndex );
		return keyIndex;
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

	public static void main( String[] args )
	{
		Integer[] a = { 3, 2, 3, -3, -4, -6 };

		// 在数组a中找到第k小的元素,k=0,1,2,3...a.length-1
		System.out.println( select( a, 1 ) );
		System.out.println( selectR( a, 1 ) );

		System.out.println( select( a, 2 ) );
		System.out.println( selectR( a, 2 ) );

		System.out.println( select( a, 3 ) );
		System.out.println( selectR( a, 3 ) );

		System.out.println( select( a, 4 ) );
		System.out.println( selectR( a, 4 ) );

		System.out.println( select( a, 5 ) );
		System.out.println( selectR( a, 5 ) );

		System.out.println( select( a, 6 ) );
		System.out.println( selectR( a, 6 ) );
	}

}
