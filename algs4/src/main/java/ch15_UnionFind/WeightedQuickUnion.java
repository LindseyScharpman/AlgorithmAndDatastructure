package ch15_UnionFind;

import edu.princeton.cs.algs4.In;

public class WeightedQuickUnion
{
	private int[] id; // 连通分量id,以触点作为索引
	private int count; // 连通分量数量
	private int[] weight; // 各个根节点对应分量的权重,触点作为索引

	// 初始化N个互相不相连触点
	public WeightedQuickUnion( int N )
	{
		count = N;
		id = new int[N];
		weight = new int[N];

		for( int i = 0; i < N; ++i )
		{
			id[i] = i;
			weight[i] = 1;
		}
	}

	// 在v w之间添加一条连接
	public void union( int v, int w )
	{
		int vRootId = find( v );
		int wRootId = find( w );

		// v w已经连接
		if( vRootId == wRootId )
			return;

		int vWeight = weight[v];
		int wWeight = weight[w];
		if(vWeight > wWeight)
		{
			id[wRootId] = vRootId;
			weight[v] += weight[w];
		}
		else
		{
			id[vRootId] = wRootId;
			weight[w] += weight[v];
		}
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
		while( x != id[x] )
			x = id[x];
		return x;
	}

	// 返回连通分量数量
	public int count()
	{
		return count;
	}

	/*
	 * java WeightedQuickUnion
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
		WeightedQuickUnion wun = new WeightedQuickUnion( N );

		while( !in.isEmpty() )
		{
			int v = in.readInt();
			int w = in.readInt();
			if( wun.connected( v, w ) )
				continue;
			wun.union( v, w );
			System.out.println( v + "--" + w );
		}
		System.out.println( "连通分量个数:" + wun.count() );
	}
}
