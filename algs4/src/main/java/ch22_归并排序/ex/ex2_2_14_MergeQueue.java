package ch22_归并排序.ex;

import edu.princeton.cs.algs4.Queue;

//归并两个有序的队列
public class ex2_2_14_MergeQueue<T extends Comparable<T>>
{

	public Queue<T> merge(Queue<T> q1, Queue<T> q2)
	{
		Queue<T> q = new Queue<T>();
		while(!q1.isEmpty() || !q2.isEmpty())
		{
			if(q1.isEmpty())
				q.enqueue( q2.dequeue() );
			else if(q2.isEmpty())
				q.enqueue( q1.dequeue() );
			else if( q1.peek().compareTo( q2.peek() ) <= 0 )
				q.enqueue( q1.dequeue() );
			else
				q.enqueue( q2.dequeue() );
		}
		return q;
	}
	
	public static void main( String[] args )
	{
		Queue<Integer> q1 = new Queue<Integer>();
		Queue<Integer> q2 = new Queue<Integer>();
		Queue<Integer> q = null;
		
		int N = 10;
		for(int i = 0; i < N; ++i)
		{
			q1.enqueue( i*2 );
			q2.enqueue( i*3 );
		}
		
		for(Integer it : q1)
			System.out.print( it + "  " );
		System.out.println();
		for(Integer it : q2)
			System.out.print( it + "  " );
		System.out.println();
		
		q = new ex2_2_14_MergeQueue().merge( q1, q2 );
		for(Integer it : q)
			System.out.print( it + "  " );
		System.out.println();
	}

}
