package ch15_UnionFind;

import edu.princeton.cs.algs4.In;

public class QuickUnion
{
	private int[] id; // 连通分量id,以触点作为索引
	private int count; // 连通分量数量

	// 初始化N个互相不相连触点
	public QuickUnion( int N )
	{
		count = N;
		id = new int[N];
		for( int i = 0; i < N; ++i )
			id[i] = i;
	}

	// 在v w之间添加一条连接
	public void union( int v, int w )
	{
		int vRootId = find( v );
		int wRootId = find( w );

		// v w已经连接
		if( vRootId == wRootId )
			return;

		id[vRootId] = wRootId;
		count--;
	}

	// 触点v w是否处在同一个连通分量中
	public boolean connected( int v, int w )
	{
		return find( v ) == find( w );
	}

	// 返回v所在的连通分量的标志符
	public int find( int v )
	{
		int x = v;
		while(x != id[x])
			x = id[x];
		return x;
	}

	// 返回连通分量数量
	public int count()
	{
		return count;
	}

	/*
	 * java QuickUnion
	 * 4--3
	 * 3--8
	 * 6--5
	 * 9--4
	 * 2--1
	 * 5--0
	 * 7--2
	 * 6--1
	 * 连通分量个数:2
	 */
	public static void main( String[] args )
	{
		In in = new In( "tinyUF.txt" );
		int N = in.readInt();
		QuickUnion un = new QuickUnion( N );

		while( !in.isEmpty() )
		{
			int v = in.readInt();
			int w = in.readInt();
			if( un.connected( v, w ) )
				continue;
			un.union( v, w );
			System.out.println( v + "--" + w );
		}
		System.out.println( "连通分量个数:" + un.count() );
	}
}
