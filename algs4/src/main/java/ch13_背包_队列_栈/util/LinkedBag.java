package ch13_背包_队列_栈.util;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

// 用链表实现背包，只需要把用链表实现栈的pop方法去掉即可。
public class LinkedBag< T > implements Iterable<T>
{
	private int N; // 背包大小，初始为0
	private Node firstNode = null; // 访问背包的头节点

	public LinkedBag() // 初始化一个空的背包
	{
		firstNode = null;
		N = 0;
		assert check();
	}

	private class Node
	{
		T t;
		Node next;
	}

	// 添加一个泛型T
	void add( T t )
	{
		Node newNode = new Node();
		newNode.t = t;
		newNode.next = firstNode;
		firstNode = newNode;
		++N;
		assert check();
	}

	// 背包是否为空
	public boolean isEmpty()
	{
		return firstNode == null;
	}

	// 背包中泛型T的数量
	public int size()
	{
		return N;
	}

	public Iterator<T> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<T>
	{
		private Node curNode = firstNode;

		public boolean hasNext()
		{
			return curNode != null;
		}

		public T next()
		{
			if( !hasNext() )
				throw new NoSuchElementException( "hasNext error!the Stack has underflow" );
			T t = curNode.t;
			curNode = curNode.next;
			return t;
		}

		public void remove()
		{
			throw new UnsupportedOperationException( "remove is not supported" );
		}
	}

	private boolean check()
	{
		if( N < 0 )
			return false;

		if( N == 0 )
		{
			if( firstNode != null )
				return false;
		}
		else if( N == 1 )
		{
			if( firstNode == null )
				return false;
			if( firstNode.next != null )
				return false;
		}
		else
		{
			if( firstNode == null )
				return false;
			if( firstNode.next == null )
				return false;
		}

		// check internal consistency of instance variable N
		int numberOfNodes = 0;
		for( Node x = firstNode; x != null && numberOfNodes <= N; x = x.next )
		{
			numberOfNodes++;
		}
		if( numberOfNodes != N )
			return false;

		return true;
	}

	public static void main( String[] args )
	{
		LinkedBag<String> s;
		s = new LinkedBag<String>();
		while( !StdIn.isEmpty() )
		{
			String item = StdIn.readString();
			s.add( item );
		}

		Iterator<String> it = s.iterator();
		while( it.hasNext() )
		{
			StdOut.println( it.next() + "\t" );
		}
	}
}