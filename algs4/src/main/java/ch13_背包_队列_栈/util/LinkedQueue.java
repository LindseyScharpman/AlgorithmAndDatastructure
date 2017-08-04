package ch13_背包_队列_栈.util;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

//用链表实现队列FIFO (first in first out)
public class LinkedQueue< T > implements Iterable<T>
{
	private int N = 0; // 队列大小，初始为0
	private Node firstNode = null; // 队列头部，链表的头部节点
	private Node lastNode = null; // 队列尾部，链表的尾部节点

	public LinkedQueue() // 初始化一个空栈
	{
		firstNode = null;
		lastNode = null;
		N = 0;
		assert check();
	}

	private class Node
	{
		T t;
		Node next;
	}

	// 插入到队列的尾部
	public void enqueue( T t )
	{
		Node newNode = new Node();
		newNode.t = t;
		newNode.next = null;

		if( isEmpty() )
		{
			firstNode = newNode;
			lastNode = newNode;
		}
		else
		{
			lastNode.next = newNode;
			lastNode = newNode;
		}
		++N;
		assert check();
	}

	// 从队列头部移除掉，返回值为移除掉的数据
	public T dequeue()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "Stack underflow" );

		T t = firstNode.t;
		firstNode = firstNode.next;
		if( firstNode == null ) // 如果队列最后一个元素被移除，则需要更新lastNode
			lastNode = null;
		--N;
		assert check();
		return t;
	}

	public T peek()
	{
		if( isEmpty() )
			throw new NoSuchElementException( "Queue underflow" );
		return firstNode.t;
	}

	// 栈是否为空
	public boolean isEmpty()
	{
		return ( firstNode == null ) && ( lastNode == null );
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
		{
			return false;
		}
		else if( N == 0 )
		{
			if( firstNode != null )
				return false;
			if( lastNode != null )
				return false;
		}
		else if( N == 1 )
		{
			if( firstNode == null || lastNode == null )
				return false;
			if( firstNode != lastNode )
				return false;
			if( firstNode.next != null )
				return false;
		}
		else
		{
			if( firstNode == null || lastNode == null )
				return false;
			if( firstNode == lastNode )
				return false;
			if( firstNode.next == null )
				return false;
			if( lastNode.next != null )
				return false;

			// check internal consistency of instance variable N
			int numberOfNodes = 0;
			for( Node x = firstNode; x != null && numberOfNodes <= N; x = x.next )
			{
				numberOfNodes++;
			}
			if( numberOfNodes != N )
				return false;

			// check internal consistency of instance variable lastNode
			Node lastNode = firstNode;
			while( lastNode.next != null )
			{
				lastNode = lastNode.next;
			}
			if( lastNode != this.lastNode )
				return false;
		}
		return true;
	}

	public static void main( String[] args )
	{
		LinkedQueue<String> s;
		s = new LinkedQueue<String>();
		while( !StdIn.isEmpty() )
		{
			String item = StdIn.readString();
			if( !item.equals( "-" ) )
				s.enqueue( item );
			else if( !s.isEmpty() )
				StdOut.print( s.dequeue() + " " );
		}

		StdOut.println( "(" + s.size() + " left on stack)" );

		Iterator<String> it = s.iterator();
		while( it.hasNext() )
		{
			StdOut.println( it.next() + "\t" );
		}
	}
}