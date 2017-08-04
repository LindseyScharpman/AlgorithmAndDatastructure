package ch21_初级排序算法;

public class Insertion
{
	public static void sort( Comparable[] a )
	{
		int len = a.length;
		for( int i = 1; i < len; ++i )
		{
			for( int j = i; j > 0 && less( a[j], a[j - 1] ); --j )
				exch( a, j, j - 1 );
		}
	}

	public static void sort2( Comparable[] a )
	{
		int len = a.length;

		// 找出最小元素放在数组的最左边
		int exchanges = 0; // 如果值没变,说明数组有序,前提是该值没有溢出
		for( int i = len - 1; i > 0; --i )
		{
			if( less( a[i], a[i - 1] ) )
			{
				exch( a, i, i - 1 );
				++exchanges;
			}
		}
		if( exchanges == 0 )
			return;

		// a[0]已经是最小的值,那么while循环体内就可以去掉 j > 0的判断条件
		for( int i = 2; i < len; ++i )
		{
			Comparable c = a[i];
			int j = i;
			while( less( c, a[j - 1] ) )
			{
				a[j] = a[j - 1];
				--j;
			}
			a[j] = c;
		}
	}

	private static void exch( Comparable[] a, int i, int j )
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static boolean less( Comparable a, Comparable b )
	{
		return a.compareTo( b ) < 0;
	}

	public static void show( Comparable[] a )
	{
		for( int i = 0; i < a.length; ++i )
			System.out.print( a[i] + " " );
		System.out.println();
	}

	public static boolean isSorted( Comparable[] a )
	{
		for( int i = 1; i < a.length; ++i )
			if( less( a[i], a[i - 1] ) )
				return false;
		return true;
	}

	public static void main( String[] args )
	{
		String[] a = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		sort( a );
		System.out.println( isSorted( a ) );
		show( a );
	}
}
