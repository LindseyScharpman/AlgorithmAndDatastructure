package ch24_优先队列;

import ch24_优先队列.MaxPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;

//从N个输入中找到M个最小的元素
public class TopM
{
	public static void main( String[] args )
	{
		int M = 3;
		MaxPQ<Integer> pq = new MaxPQ<Integer>( M + 1 );
		while( !StdIn.isEmpty() )
		{
			pq.insert( StdIn.readInt() );
			if( pq.size() > M )
				pq.delMax();
		}

		Stack<Integer> s = new Stack<Integer>();
		while( !pq.isEmpty() )
			s.push( pq.delMax() );
		for( Integer i : s )
			System.out.println( i );
	}
}
