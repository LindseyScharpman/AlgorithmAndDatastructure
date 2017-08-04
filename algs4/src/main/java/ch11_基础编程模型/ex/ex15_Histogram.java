package ch11_基础编程模型.ex;
public class ex15_Histogram
{
	// 对于数组arr[],统计其中每个数字出现的次数，并存放在返回的数组里面
	// M[0]的值表示0在数组arr[]中出现的次数
	public static int[] histogram( int arr[], int m )
	{
		int M[] = new int[m];
		for( int i = 0; i < M.length; ++i )
			M[i] = 0;

		for( int i = 0; i < arr.length; ++i )
			M[arr[i]] += 1;
		return M;
	}

	public static void main( String[] args )
	{
		int arr[] = { 1, 2, 0, 4, 5, 6, 6, 7, 7, 7, 8, 9 };
		int maxNum = arr[0];
		for( int i = 0; i < arr.length; ++i )
		{
			if( arr[i] > maxNum )
				maxNum = arr[i];
		}

		int[] M = histogram( arr, maxNum + 1 );
		for( int i = 0; i < M.length; ++i )
		{
			System.out.println( i + " 出现次数:" + M[i] );
		}
	}
}