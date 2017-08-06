package ch31_符号表;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

//基于无序链表的顺序查找
public class SequentialSearchST<Key, Value>
{
	private Node first = null;
	private int N = 0;
	
	private class Node
	{
		//链表节点的定义
		Key key;
		Value val;
		Node next;
		
		Node(Key key, Value val, Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}
	
	//获取key对应的值,若key不存在,则返回null
	public Value get(Key key)
	{
		if( key == null )
			throw new NullPointerException("get's argument is null");
		
		//查找给定的键
		for(Node x = first; x != null; x = x.next)
		{
			if( x.key.equals(key) )
				return x.val;			//命中
		}
		return null;					//未命中
	}
	
	//将键值对存入表中,若值为空则将键key从表中删除
	public void put(Key key, Value val)
	{
		if( key == null )
			throw new NullPointerException("put's argument is null");
		
		if( val == null)
		{
			deleteR(key);
			return;
		}
		
		for(Node x = first; x != null; x = x.next)
		{
			if( x.key.equals(key) )
			{
				x.val = val;			//命中,更新
				return;
			}
		}
		
		//未命中,新建节点插入到链表
		first = new Node(key, val, first);
		++N;
	}
	
	//从表中删去键key及其对应的值
	public void delete(Key key)
	{
		if( key == null )
			throw new NullPointerException("delete's argument is null");
		if( N <= 0 )
			throw new RuntimeException("has no element to delete");
		
		//删除头节点
		if( first.key.equals(key) )
		{
			first = first.next;
			--N;
			return;
		}
		
		for(Node x = first, pre = first; x != null; pre = x,x = x.next)
		{
			if( x.key.equals(key) )
			{
				pre.next = x.next;
				--N;
				return;
			}
		}
	}
	//递归写法删除
	public void deleteR(Key key)
	{
		first = deleteR_(first, key);
	}
	private Node deleteR_(Node x, Key key)
	{
		if(x == null)
			return null;
		if(key.equals(x.key))
		{
			--N;
			return x.next;
		}
		x.next = deleteR_(x.next, key);
		return x;
	}
	
	//表中键值对的数量
	public int size(){ return N; }
	//是否为空,空返回true
	public boolean isEmpty(){ return size() == 0; }
	
	//键key是否存在在表中
	public boolean contains(Key key)
	{
		if( key == null )
			throw new NullPointerException("contains'argument is null");
		return get(key) != null;
	}
	
	//返回表中所有键的集合
	public Iterable<Key> keys()
	{
		List<Key> list = new ArrayList<Key>();
		for(Node x = first; x != null; x = x.next)
			list.add(x.key);
		return list;
	}
	
	public static void main(String[] args) 
	{
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
		for( int i = 0; !StdIn.isEmpty(); ++i )
		{
			String str = StdIn.readString();
			st.put(str, i);
		}
		
		st.put("L", null);
		for( String s:st.keys() )
			StdOut.println( s + " " + st.get(s) );
		StdOut.println( "st size:" + st.size() );
		
	}
}
