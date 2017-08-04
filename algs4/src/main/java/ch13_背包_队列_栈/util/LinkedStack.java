package ch13_背包_队列_栈.util;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

// 所需空间和集合大小成正比
// 所需的时间总是常数，和机会大小无关
public class LinkedStack< T > implements Iterable<T>
{
	private int N; // 已用栈的大小，初始为0
	private Node firstNode = null; // 栈顶，链表的头部节点

	public LinkedStack() // 初始化一个空栈
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
	public void push( T t )
	{
		Node newNode = new Node();
		newNode.t = t;
		newNode.next = firstNode;
		firstNode = newNode;
		++N;
		assert check();
	}

	// 删除最近添加的泛型T
	public T pop()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "pop：stack underflow" );
		T t = firstNode.t;
		firstNode = firstNode.next;
		--N;
		assert check();
		return t;
	}

	// 返回栈顶的元素，但不把此元素弹出栈
	public T peek()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "peek：stack underflow" );
		return firstNode.t;
	}

	// 栈是否为空
	public boolean isEmpty()
	{
		return firstNode == null;
	}

	// 栈中的泛型T的数量
	public int size()
	{
		return N;
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();
		for( T item : this )
			s.append( item + " " );
		return s.toString();
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

	// 没看懂题意，这里就是实现拷贝
	public static LinkedStack<String> copy( LinkedStack<String> s )
	{
		LinkedStack<String> copys = new LinkedStack<String>();
		LinkedStack<String> tmp = new LinkedStack<String>();

		// 暂时存在一个临时栈里面
		int size = s.size();
		for( int i = 0; i < size; ++i )
		{
			tmp.push( s.pop() );
		}

		// 复制栈，和原栈有相同的顺序
		size = tmp.size();
		for( int i = 0; i < size; ++i )
		{
			copys.push( tmp.pop() );
		}
		return copys;
	}

	public static void main( String[] args )
	{
		LinkedStack<String> s;
		s = new LinkedStack<String>();
		while( !StdIn.isEmpty() )
		{
			String item = StdIn.readString();
			if( !item.equals( "-" ) )
				s.push( item );
			else if( !s.isEmpty() )
				StdOut.print( s.pop() + " " );
		}
		StdOut.println( "(" + s.size() + " left on stack)" );

		StdOut.println( "peek:" + s.peek() );

		s.push( "test" );
		s.push( "hello" );
		Iterator<String> it = s.iterator();
		StdOut.println( "print the current stack" );
		while( it.hasNext() )
		{
			StdOut.println( it.next() + "\t" );
		}

		LinkedStack<String> copys = LinkedStack.copy( s );
		Iterator<String> cit = copys.iterator();
		StdOut.println( "print the current copy of stack" );
		while( cit.hasNext() )
		{
			StdOut.println( cit.next() + "\t" );
		}
	}
}