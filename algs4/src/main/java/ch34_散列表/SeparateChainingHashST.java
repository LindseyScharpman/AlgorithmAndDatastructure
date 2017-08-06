package ch34_散列表;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class SeparateChainingHashST<Key, Value> 
{
	private static final int INIIT_CAPACITY = 5;
	private int N;					//键值对总数
	private int M;					//散列表大小
	private SequentialSearchST<Key, Value>[] st;	//存放链表对象的数组
	
	// largest prime <= 2^i for i = 3 to 31
    // not currently used for doubling and shrinking
    // private static final int[] PRIMES = {
    //    7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
    //    32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
    //    8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
    //    536870909, 1073741789, 2147483647
    // };
	
	public SeparateChainingHashST()
	{
		this(INIIT_CAPACITY);
	}
	
	//创建M条链表
	public SeparateChainingHashST(int M)
	{
		this.M = M;
		
		st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
		for(int i = 0; i < M; ++i)
			st[i] = new SequentialSearchST<Key, Value>();
	}
	
	public Value get(Key key)
	{
		if(key == null)
			throw new NullPointerException("get");
		return st[hash(key)].get(key);
	}
	
	public void put(Key key, Value val)
	{
		if (key == null) 
			throw new NullPointerException("put");
		if(val == null)
		{
			delete(key);
			return;
		}
		
		if (N >= 10*M) 
			resize(2*M);
		
		int i = hash(key);
		if( !contains(key) )		//插入新的键值对 
			N++;
		st[i].put(key, val);
	}
	
	public void delete(Key key)
	{
		if (key == null) 
			throw new NullPointerException("delete");
		
		int i = hash(key);
		if ( st[i].contains(key) ) 
			N--;
		st[i].delete(key);
		
		if( M > INIIT_CAPACITY && N <= 2*M )
			resize(M/2);
	}
	
	public Iterable<Key> keys()
	{
		List<Key> keys = new ArrayList<Key>();
		for(int i = 0; i < M; ++i)
		{
			List<Key> currentList = (List<Key>) st[i].keys();
			for(Iterator<Key> it = currentList.iterator(); it.hasNext(); )
			{
				Key k = it.next();
				keys.add(k);
			}
		}
		return keys;
	}
	
	private void resize(int size)
	{
		SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<Key, Value>(size);
		for(int i = 0; i < M; ++i)
		{
			for( Key key:st[i].keys() )
				temp.put( key, st[i].get(key) );
		}
		this.M = temp.M;
		this.N = temp.N;
		this.st = temp.st;
	}
	
	//打印出每条链表的内容
	public void printChains()
	{
		List<Key> keys = new ArrayList<Key>();
		for(int i = 0; i < M; ++i)
		{
			List<Key> currentList = (List<Key>) st[i].keys();
			System.out.print("Chain" + i + ":");
			for(Iterator<Key> it = currentList.iterator(); it.hasNext(); )
			{
				Key k  =it.next();
				keys.add(k);
				System.out.print(k + ":" + get(k) + "-->");
			}
			System.out.println("");
		}
	}
	
	//hash值在[0...M-1]
	private int hash(Key key){ return ( key.hashCode() & 0x7fffffff ) % M; }
	//总的键值对个数
	public int size() { return N; }
	public boolean isEmpty() { return size() == 0; }
	public boolean contains(Key key)
	{
		if(key == null)
			throw new NullPointerException("contains");
		return get(key) != null;
	}
	
	public static void main(String[] args) 
	{
		SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

		st.printChains();
    }
}
