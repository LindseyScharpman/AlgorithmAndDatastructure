package ch14_算法分析.ex;

//1.4.20 双调查找
public class DoubleMonotonous
{
	// arr为双调数组,所有元素先递增后递减
	// 返回首次递减的元素的索引
	public static int findBound( int[] arr )
	{
		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while( lo < hi )
		{
			mid = ( lo + hi ) / 2;
			if( arr[mid] > arr[mid - 1] )
				lo = mid + 1;
			else
				hi = mid;
		}
		return ( lo + hi ) / 2;
	}

	// 数组arr必须有序,此处假设了是从低到高排列
	// 对于数组,注意访问越界问题,下标值最大为arr.length-1
	public static int rankASC( int key, int[] arr, int lo, int hi )
	{
		while( lo <= hi )
		{
			int mid = lo + ( hi - lo ) / 2;
			if( key < arr[mid] )
				hi = mid - 1;
			else if( key > arr[mid] )
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	// 数组arr必须有序,此处假设了是从高到低排列
	// 对于数组,注意访问越界问题,下标值最大为arr.length-1
	public static int rankDESC( int key, int[] arr, int lo, int hi )
	{
		while( lo <= hi )
		{
			int mid = lo + ( hi - lo ) / 2;
			if( key < arr[mid] )
				lo = mid + 1;
			else if( key > arr[mid] )
				hi = mid - 1;
			else
				return mid;
		}
		return -1;
	}

	public static int findKey( int key, int[] arr )
	{
		int bound = findBound( arr );

		int f1 = rankASC( key, arr, 0, bound - 1 );
		int f2 = rankDESC( key, arr, bound, arr.length - 1 );
		// System.out.println(f1 + "\t" + f2);
		if( f1 == -1 && f2 == -1 )
			return -1;
		return f1 * f2 * ( -1 );
	}

	public static void main( String[] args )
	{
		int[] arr = { 0, 1, 2, 3, 4, 5, -4, -6, -8 };
		System.out.println( "bound index = " + findBound( arr ) );
		System.out.println( "key index = " + findKey( 4, arr ) );
	}

}
