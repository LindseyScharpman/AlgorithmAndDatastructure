package ch13_背包_队列_栈.util;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class ResizingArrayQueue< T > implements Iterable<T>
{
	private T[] q = null; // 队列
	private int N = 0; // 队列的长度
	private int head = 0; // 队列头
	private int tail = 0; // 队列尾

	@SuppressWarnings("unchecked")
	public ResizingArrayQueue()
	{
		q = (T[]) ( new Object[5] );
		N = 0;
		head = 0;
		tail = 0;
	}

	// 将数据T入队
	public void enqueue( T t )
	{
		++N;
		if( N == q.length ) // 队列满了，扩展队列
			resize( 2 * q.length );

		q[tail++] = t;
	}

	// 出队，并返回出队的数据
	public T dequeue()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "dequeue:queue underflow" );

		--N;
		if( N > 0 && N == q.length / 4 )
			resize( q.length / 2 );

		T t = q[head];
		q[head] = null; // 避免游离对象
		++head;
		return t;
	}

	// 队列是否为空
	public boolean isEmpty()
	{
		return N == 0;
	}

	// 队列长度
	public int size()
	{
		return N;
	}

	private void resize( int size )
	{
		assert size >= N;
		@SuppressWarnings("unchecked")
		T[] newQ = (T[]) ( new Object[size] );
		int i, j;
		for( i = 0, j = head; j < tail; ++i, ++j )
		{
			newQ[i] = q[j];
		}
		tail = tail - head;
		head = 0;
		q = newQ;
	}

	public Iterator<T> iterator()
	{
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<T>
	{
		private int i = head;
		private int next = N;

		public boolean hasNext()
		{
			return next != 0;
		}

		public T next()
		{
			if( !hasNext() )
				throw new NoSuchElementException( "next:no element" );
			T t = q[i++];
			--next;
			return t;
		}

		public void remove()
		{
			throw new UnsupportedOperationException( "remove:unsupported" );
		}
	}

	public static void main( String[] args )
	{
		ResizingArrayQueue<String> q = new ResizingArrayQueue<String>();
		while( !StdIn.isEmpty() )
		{
			String item = StdIn.readString();
			if( !item.equals( "-" ) )
				q.enqueue( item );
			else if( !q.isEmpty() )
				StdOut.print( q.dequeue() + " " );
		}

		StdOut.println( "(" + q.size() + " left on queue)" );
		Iterator<String> it = q.iterator();
		while( it.hasNext() )
		{
			StdOut.println( it.next() + "\t" );
		}
	}
}