package ch24_优先队列;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ< Key extends Comparable<Key>> implements Iterable<Integer>
{
	private int maxN; // 优先队列中元素的最大个数
	private int N; // 优先队列中元素的个数

	// pq[1...N]为优先队列中元素,pq[1]是keys[]数组中最小值的下标
	private int[] pq;

	// 比如说要删除keys[4],那么qp[4]的值记录着keys[4]在pq中下标
	private int[] qp;
	private Key[] keys; // keys[i] = priority of i

	// 构造能放下maxN个元素的优先队列
	public IndexMinPQ( int maxN )
	{
		if( maxN < 0 )
			throw new IllegalArgumentException();
		this.maxN = maxN;
		pq = new int[maxN+1]; // pq[0]不使用
		qp = new int[maxN];
		keys = (Key[]) new Comparable[maxN];
		for( int i = 0; i < maxN; ++i )
			qp[i] = -1;

	}

	public boolean isEmpty()
	{
		return N == 0;
	}

	// 是否存在索引为k的元素
	public boolean contains( int k )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException();
		return qp[k] != -1;
	}

	public int size()
	{
		return N;
	}

	// 插入元素key,将它和索引值k关联
	public void insert( int k, Key key )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException( "insert" );
		if( contains( k ) )
			throw new IllegalArgumentException( "insert" );

		++N;
		pq[N] = k; // pq[N]的值为key在keys[]数组里面的索引k
		qp[k] = N;
		keys[k] = key;
		swim( N );
	}

	public int minIndex()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "minIndex" );
		return pq[1];
	}

	public Key minKey()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "minIndex" );
		return keys[pq[1]];
	}

	// 删除最小元素,并返回它在keys[]数组的索引
	public int delMin()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "delMin" );

		int indexOfMin = pq[1];
		exch( 1, N-- );
		sink( 1 );
		keys[pq[N + 1]] = null;
		qp[pq[N + 1]] = -1;
		return indexOfMin;
	}

	// 返回keys[]数组中索引为k的元素
	public Key keyOf( int k )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException( "keyOf" );
		if( !contains( k ) )
			throw new NoSuchElementException( "keyOf" );
		return keys[k];
	}

	// 把keys[k]的值设为key
	public void changeKey( int k, Key key )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException( "changeKey" );
		if( !contains( k ) )
			throw new NoSuchElementException( "changeKey" );
		keys[k] = key;
		swim( qp[k] );
		sink( qp[k] );
	}

	public void change( int k, Key key )
	{
		changeKey( k, key );
	}

	// 把keys[k]的key值降到key
	public void decreaseKey( int k, Key key )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException( "decreaseKey" );
		if( keys[k].compareTo( key ) <= 0 )
			throw new NoSuchElementException( "decreaseKey" );
		keys[k] = key;
		swim( qp[k] );
	}

	// 把keys[k]的key值增到key
	public void increaseKey( int k, Key key )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException( "decreaseKey" );
		if( keys[k].compareTo( key ) >= 0 )
			throw new NoSuchElementException( "decreaseKey" );
		keys[k] = key;
		sink( qp[k] );
	}

	// 删除k及其关联的元素keys[k]
	public void delete( int k )
	{
		if( k < 0 || k >= maxN )
			throw new IndexOutOfBoundsException("ch11");
		if( !contains( k ) )
			throw new NoSuchElementException("ch11");

		int index = qp[k];
		exch( index, N-- );
		qp[pq[N + 1]] = -1;
		keys[pq[N + 1]] = null;
		swim( index );
		sink( index );
	}

	// 堆中位置i上所关联的元素大于j关联的元素
	// pq[i],pq[j]是关联的元素的索引
	private boolean greater( int i, int j )
	{
		return keys[pq[i]].compareTo( keys[pq[j]] ) > 0;
	}

	// 交换堆中位置i上所关联的元素和j关联的元素,由于堆中存放的是元素索引,
	// 因此交换这些索引即可,同时需要更新qp中的值
	private void exch( int i, int j )
	{
		int t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;

		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}

	private void swim( int k )
	{
		while( k > 1 && greater( k / 2, k ) )
		{
			exch( k, k / 2 );
			k = k / 2;
		}
	}

	private void sink( int k )
	{
		while( 2 * k <= N )
		{
			int j = 2 * k;
			if( j < N && greater( j, j + 1 ) )
				j++;
			if( !greater( k, j ) )
				break;
			exch( k, j );
			k = j;
		}
	}

	public Iterator<Integer> iterator()
	{
		return new HeapIterator();
	}

	private class HeapIterator implements Iterator<Integer>
	{
		// create a new pq
		private IndexMinPQ<Key> copy;

		// add all elements to copy of heap
		// takes linear time since already in heap order so no keys move
		public HeapIterator()
		{
			copy = new IndexMinPQ<Key>( pq.length - 1 );
			for( int i = 1; i <= N; i++ )
				copy.insert( pq[i], keys[pq[i]] );
		}

		public boolean hasNext()
		{
			return !copy.isEmpty();
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		public Integer next()
		{
			if( !hasNext() )
				throw new NoSuchElementException();
			return copy.delMin();
		}
	}

	public static void main( String[] args )
	{
		// insert a bunch of strings
		String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

		IndexMinPQ<String> pq = new IndexMinPQ<String>( strings.length );
		for( int i = 0; i < strings.length; i++ )
			pq.insert( i, strings[i] );

		// delete and print each key
		while( !pq.isEmpty() )
		{
			int i = pq.delMin();
			System.out.println( i + " " + strings[i] );
		}
		System.out.println();

		// reinsert the same strings
		for( int i = 0; i < strings.length; i++ )
		{
			pq.insert( i, strings[i] );
		}

		// print each key using the iterator
		for( int i : pq )
		{
			System.out.println( i + " " + strings[i] );
		}
		while( !pq.isEmpty() )
		{
			pq.delMin();
		}
	}
}

