package ch21_初级排序算法;

public class Shell
{
	public static void sort(Comparable[] a)
	{
		int len = a.length;
		int h = 1;
		while( h < len / 3 )
			h = h * 3 + 1;

		while( h >= 1 )
		{
			for( int i = h; i < len; ++i )
			{
				for( int j = i; j >= h && less( a[j], a[j - h] ); j -= h )
					exch( a, j, j - h );
			}

			h /= 3;
		}
	}

	private static void exch(Comparable[] a, int i, int j)
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	private static boolean less(Comparable a, Comparable b)
	{
		return a.compareTo( b ) < 0;
	}

	public static void show(Comparable[] a)
	{
		for( int i = 0; i < a.length; ++i )
			System.out.print( a[i] + " " );
		System.out.println();
	}

	public static boolean isSorted(Comparable[] a)
	{
		for( int i = 1; i < a.length; ++i )
			if( less( a[i], a[i - 1] ) )
				return false;
		return true;
	}

	public static void main(String[] args)
	{
		String[] a = { "S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E" };
		sort( a );
		System.out.println( isSorted( a ) );
		show( a );
	}
}
