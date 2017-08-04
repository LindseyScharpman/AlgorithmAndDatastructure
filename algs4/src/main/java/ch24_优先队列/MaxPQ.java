package ch24_优先队列;

import java.util.NoSuchElementException;

//基于堆的优先队列,插入或者删除均在lgN时间内完成
public class MaxPQ< Key extends Comparable<Key>>
{
	private Key[] pq; // 基于堆的完全二叉树
	private int N = 0; // 存储于pq[1...N]中,pq[0]没有被使用

	//构造一个空的优先队列
	public MaxPQ()
	{
		this( 10 );
	}

	// 构造容量为maxN个的空优先队列
	public MaxPQ( int maxN )
	{
		assert maxN > 1;
		pq = (Key[]) new Comparable[maxN + 1];
		N = 0;
	}

	//以keys数组为元素构造堆
	public MaxPQ( Key[] keys )
	{
		N = keys.length;
		pq = (Key[]) new Comparable[N + 1];

		for( int i = 0; i < keys.length; ++i )
			pq[i + 1] = keys[i];
		for( int k = keys.length / 2; k >= 1; --k )
			sink( k );
		assert isMaxHeap();
	}

	// 向优先队列pq[1...N]中插入一个新元素,即插入到pq[N+1]的位置
	public void insert( Key key )
	{
		if( N >= pq.length - 1 )
			resize( pq.length * 2 );
		pq[++N] = key;
		swim( N );
		assert isMaxHeap();
	}

	// 删除并返回最大的元素
	public Key delMax()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "delMax" );
		Key max = pq[1];
		swap( pq, 1, N );
		--N;
		pq[N + 1] = null; // 避免游离对象
		sink( 1 );

		if( N > 0 && ( N == ( pq.length - 1 ) / 4 ) )
			resize( pq.length / 2 );
		
		assert isMaxHeap();
		return max;
	}

	// 把位置k的元素上浮,使得堆有序
	private void swim( int k )
	{
		while( k > 1 && less( pq[k / 2], pq[k] ) )
		{
			swap( pq, k / 2, k );
			k /= 2;
		}
	}

	// 把位置k的元素下沉,使得堆有序
	private void sink( int k )
	{
		while( 2 * k <= N )
		{
			int j = 2 * k;
			// 找出子节点中较大者
			if( j < N && less( pq[j], pq[j + 1] ) )
				++j;
			// 若大于等于子节点,说明此时堆已经有序
			if( !less( pq[k], pq[j] ) )
				break;
			swap( pq, k, j );
			k = j;
		}
	}

	private void resize( int size )
	{
		assert size > N;
		Key[] tmp = (Key[]) new Comparable[size];
		for( int i = 1; i <= N; ++i )
			tmp[i] = pq[i];
		pq = tmp;

	}

	// 优先队列是否为空
	public boolean isEmpty()
	{
		return N == 0;
	}

	// 返回优先队列中元素的个数
	public int size()
	{
		return N;
	}

	// 返回优先队列中的最大元素
	public Key max()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "max" );
		return pq[1];
	}

	/***************************************************************************
	 * 辅助函数
	 **************************************************************************/
	private boolean less( Comparable a, Comparable b )
	{
		return a.compareTo( b ) < 0;
	}

	private void swap( Comparable a[], int i, int j )
	{
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// pq[1..N]是最大堆吗
	private boolean isMaxHeap()
	{
		return isMaxHeap( 1 );
	}

	private boolean isMaxHeap( int k )
	{
		if( k > N )
			return true;
		int left = 2 * k, right = 2 * k + 1;
		if( left <= N && less( pq[k], pq[left] ) )
			return false;
		if( right <= N && less( pq[k], pq[right] ) )
			return false;
		return isMaxHeap( left ) && isMaxHeap( right );
	}

	public void show()
	{
		for(int i = 1; i <=N; ++i)
			System.out.print(pq[i] +"  ");
		System.out.println();
	}
	public static void main( String[] args )
	{
		MaxPQ<String> pq = new MaxPQ<String>();
		String items[] = { "P", "Q", "E", "-", "X", "A", "M", "-", "P", "L", "E", "-" };
		for( int i = 0; i < items.length; ++i )
		{
			String item = items[i];
			if( !item.equals( "-" ) )
			{
				pq.insert( item );
			}
			else if( !pq.isEmpty() )
				System.out.print( pq.delMax() + " " );
		}
		System.out.println( "(" + pq.size() + " left on pq)" );

	}

}
