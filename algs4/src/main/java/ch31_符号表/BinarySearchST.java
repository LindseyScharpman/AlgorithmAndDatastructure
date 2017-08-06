package ch31_符号表;

import java.util.ArrayList;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class BinarySearchST<Key extends Comparable<Key>, Value> 
{
	private static final int INII_CAPACITY = 16;
	private Key[] keys;
	private Value[] vals;
	private int N;				//键值对数量
	
	public BinarySearchST()
	{
		this(INII_CAPACITY);
	}
	public BinarySearchST(int capacity)
	{
		keys = (Key[])new Comparable[capacity];
		vals = (Value[])new Object[capacity];
	}
	
	//返回小于key键的数量,即使不存在该键,也返回表中小于它的键的数量
	public int rank(Key key)
	{
		if(key == null)
			throw new NullPointerException("rank's argument is null");
		
		int lo = 0, hi = N - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if( keys[mid].compareTo(key) < 0 )
				lo = mid + 1;
			else if( keys[mid].compareTo(key) > 0 )
				hi = mid - 1;
			else 
				return mid;
		}
		return lo;
	}
	
	public Value get(Key key)
	{
		if(key == null)
			throw new NullPointerException("getl");
		if( isEmpty() )
			return null;
		
		int i = rank(key);			//小于key键的数量
		if( i < N && keys[i].compareTo(key) == 0 )
			return vals[i];
		else
			return null;
	}
	
	public void put(Key key, Value val)
	{
		if(key == null)
			throw new NullPointerException("put");
		if(val == null)
		{
			delete(key);
			return;
		}
		
		int i = rank(key);			//小于key键的数量
		//键值对存在表中,更新值
		if( i < N && keys[i].compareTo(key) == 0 )
		{
			vals[i] = val;
			return;
		}
		
		if(N == keys.length)		//调整数组大小
			resize(2*N);
		
		//在位置i处插入新的键值对
		for(int j = N; j > i; --j)
		{
			keys[j] = keys[j-1];
			vals[j] = vals[j-1];
		}
		keys[i] = key;
		vals[i] = val;
		++N;
		
		assert check();
	}
	
	public void delete(Key key) 
	{
		if(key == null)
			throw new NullPointerException("delete's argument is null");
		if( isEmpty() )
			return;
		
		int i = rank(key);			//小于key键的数量
		
		//键不存在在表中
		if(i == N || keys[i].compareTo(key) != 0)
			return;
		
		//键存在表中
		if( i < N && key.compareTo(keys[i]) == 0 )
		{
			//删除位置i上的键,把位置i之后的键往前挪动一格
			for(int j = i; j < N - 1; j++)
			{
				keys[j] = keys[j+1];
				vals[j] = vals[j+1];
			}
			--N;
			keys[N] = null;
			vals[N] = null;
		}
		
		if( N > 0 && N == keys.length/4 )
			resize(N/2);
		
		assert check();
	}

	private void resize(int size) 
	{
		assert size >= N;
		
		BinarySearchST<Key, Value> temp = new BinarySearchST<Key, Value>(size);
		for(int i = 0; i < N; ++i)
		{
			temp.keys[i] = keys[i];
			temp.vals[i] = vals[i];
		}
		keys = temp.keys;
		vals = temp.vals;
	}

	//拿到最小的键
	public Key min()
	{
		if( isEmpty() )
			return null;
		return keys[0]; 
	}
	
	//拿到最大的键
	public Key max()
	{
		if( isEmpty() )
			return null;
		return keys[N-1]; 
	}
	
	//拿到排名为k的键 k=0,1,2,3...
	public Key select(int k)
	{ 
		if(k < 0 || k >= N )
			return null;
		return keys[k]; 
	}
	
	//返回大于等于key的最小键
	public Key ceiling(Key key)
	{
		if(key == null)
			throw new NullPointerException("ceiling's argument is null");
		
		int i = rank(key);
		if(i >= N)
			return null;
		return keys[i];
	}
	
	//返回小于等于key的最大键
	public Key floor(Key key)
	{
		if(key == null)
			throw new NullPointerException("floor's argument is null");
		
		int i = rank(key);
		if( i < N && keys[i].compareTo(key) == 0 )
			return keys[i];
		if( i == 0 )
			return null;
		else
			return keys[i-1];
	}
	
	//拿到所有的键
	public Iterable<Key> keys(){ return keys(min(), max()); }
	
	//[lo...hi]之间的所有键,已排序
	public Iterable<Key> keys(Key lo, Key hi)
	{
		if(lo == null || hi == null)
			throw new NullPointerException("keys's argument is null");
		
		ArrayList<Key> list = new ArrayList<Key>();
		if( lo.compareTo(hi) > 0 )
			return list;
		
		int end = rank(hi);
		for(int start = rank(lo); start < end; ++start)
		{
			list.add(keys[start]);
		}
		if( contains(hi) )
			list.add(keys[rank(hi)]);
		return list;
	}
	
	public int size(){ return N; }
	public boolean isEmpty(){ return size() == 0; }
	public boolean contains(Key key)
	{
		if(key == null)
			throw new NullPointerException("contain's argument is null");
		return get(key) != null;
	}
	
	/***************************************************************************
    *  Check internal invariants.
    ***************************************************************************/
    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i-1]) < 0) return false;
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;
        return true;
    }


	public static void main(String[] args) 
	{
		System.out.println("\\\\");
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>(10);
	    for (int i = 0; !StdIn.isEmpty(); i++) 
	    {
	        String key = StdIn.readString();
	        st.put(key, i);
	    }
	    
	    System.out.println();
	    st.put("L", null);
	    for (String s : st.keys())
	        StdOut.println(s + " " + st.get(s));
	}

}
