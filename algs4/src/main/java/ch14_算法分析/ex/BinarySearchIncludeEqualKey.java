package ch14_算法分析.ex;

//对数级别的时间找出与键匹配的索引,如果有重复元素,返回索引最小的位置
public class BinarySearchIncludeEqualKey
{

	public static int FindKeyOfMinIndex( int key, int arr[] )
	{
		// 在arr里面找到比key刚好小的元素的索引
		// 如果比key刚好小的元素有很多个,则返回最右的那一个
		int rs = strictLowerBound( key, arr );

		if( rs == -1 )
		{
			if( key == arr[0] )
				return 0;
			else
				return -1;
		}

		// key非常大,strictLowerBound返回了整个数组最右的索引,说明key > MAX( arr ),则key不存在arr里面
		if( rs == arr.length - 1 )
			return -1;

		return rs + 1;
	}

	// 在arr里面找到比key刚好小的元素的索引
	// 如果比key刚好小的元素有很多个,则返回最右的那一个
	public static int strictLowerBound( int key, int arr[] )
	{
		if( key <= arr[0] )
			return -1;

		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while( lo < hi )
		{
			// mid计算时应该向上取整,否则lo == hi - 1且掉入了if语句时,会陷入无限循环
			mid = ( hi + lo + 1 ) / 2;
			if( arr[mid] < key )
				lo = mid;
			else
				hi = mid - 1;
			// StdOut.println(lo + " " + hi + " " + mid + "!" );
		}

		// while退出时 lo == hi,此时该位置上的元素就是刚好小于key的元素
		mid = ( hi + lo + 1 ) / 2;
		return mid; // 也可以直接return hi或者 return lo
	}

	public static void main( String[] args )
	{
		int a[] = { 1, 1, 1, 2, 2, 2, 3, 3 };

		System.out.println( FindKeyOfMinIndex( 2, a ) );
	}
}
