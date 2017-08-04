package ch13_背包_队列_栈.util;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

// 用数组实现栈，可动态调整大小
// push pop都是常数时间，和集合大小无关
// 空间需求不超过集合大小乘以一个常数
public class ResizingArrayStack< T > implements Iterable<T>
{
	@SuppressWarnings("unchecked")
	private T[] s = (T[]) (new Object[10]);; // 栈的入口,用泛型数组实现
	private int N; // 已用栈的大小，初始为0

	// 调整数组大小
	private void resize( int size )
	{
		@SuppressWarnings("unchecked")
		T[] t = (T[]) (new Object[size]); // 调整后数组
		for( int i = 0; i < N; ++i )
		{
			t[i] = s[i];
		}
		s = t;
	}

	// 添加一个泛型T
	void push( T t )
	{
		if( N == s.length ) // 栈满了，需要调整数组大小，此处我们扩展2倍
			resize( 2 * s.length );
		s[N++] = t;
	}

	// 删除最近添加的泛型T
	public T pop()
	{
		if( isEmpty() )
			throw new StackOverflowError( "pop" );

		T t = s[--N];
		s[N] = null; // 帮助Java收集游离的对象,不然可能造成OOM

		if( (N >= 0) && (N == s.length / 4) )
			resize( s.length / 2 );

		return t;
	}

	// 栈是否为空
	public boolean isEmpty()
	{
		return N == 0;
	}

	// 栈中的泛型T的数量
	public int size()
	{
		return N;
	}

	public Iterator<T> iterator()
	{
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<T>
	{
		private int i = N;

		public boolean hasNext()
		{
			return i > 0;
		}

		public T next()
		{
			return s[--i];
		}

		public void remove()
		{
			throw new UnsupportedOperationException( "remove is not supported" );
		}
	}

	public static void main( String[] args )
	{
		ResizingArrayStack<String> s;
		s = new ResizingArrayStack<String>();
		while( !StdIn.isEmpty() )
		{
			String item = StdIn.readString();
			if( !item.equals( "-" ) )
				s.push( item );
			else if( !s.isEmpty() )
				StdOut.print( s.pop() + " " );
		}
		StdOut.println( "(" + s.size() + " left on stack)" );
	}
}