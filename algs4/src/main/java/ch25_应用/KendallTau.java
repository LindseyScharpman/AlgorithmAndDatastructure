package ch25_应用;

public class KendallTau
{
	private static int count;
	private static int[] aux;

	// 没有考虑有负数的情况,如果有负数可以考虑+offset
	public static int kendallTau( int[] A, int[] B )
	{
		// 建立关联索引数组,以A数组作为标准
		int[] refA = new int[A.length];
		int[] refB = new int[B.length];
		for( int i = 0; i < A.length; ++i )
			refA[A[i]] = i;

		// 计算出B数组以A数组作为标准的排序顺序,refB[0...B.length-1]标志着其顺序.
		for( int i = 0; i < B.length; ++i )
			refB[i] = refA[B[i]];

		// 计算refB中逆序对数量,该值即为A B两个数组的kendallTau距离
		aux = new int[refB.length];
		inversePair( refB, 0, refB.length - 1 );
		int cnt = count;
		count = 0;
		return cnt;
	}

	private static void inversePair( int[] refB, int lo, int hi )
	{
		if( lo >= hi )
			return;
		int mid = ( lo + hi ) / 2;
		inversePair( refB, lo, mid );
		inversePair( refB, mid + 1, hi );
		merge( refB, lo, mid, hi );
	}

	private static void merge( int[] refB, int lo, int mid, int hi )
	{
		for( int k = lo; k <= hi; ++k )
			aux[k] = refB[k];

		int i = lo, j = mid + 1;
		for( int k = lo; k <= hi; ++k )
		{
			if( j > hi )
				refB[k] = aux[i++];
			else if( i > mid )
				refB[k] = aux[j++];
			else if( aux[i] < aux[j] )
				refB[k] = aux[i++];
			else
			{
				refB[k] = aux[j++];
				// 增加逆序对
				count += mid - i + 1;
			}
		}
	}

	private static void swap( int[] a, int i, int j )
	{
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void main( String[] args )
	{
		int[] A1 = { 0, 3, 1, 6, 2, 5, 4 };
		int[] B1 = { 1, 0, 3, 6, 4, 2, 5 };
		System.out.println( kendallTau( A1, B1 ) );
		
		
		int[] A2 = { 0, 1, 3, 2, 4, 5, 6 };
		int[] B2 = { 1, 0, 3, 6, 4, 2, 5 };
		System.out.println( kendallTau( A2, B2 ) );
	}
}
