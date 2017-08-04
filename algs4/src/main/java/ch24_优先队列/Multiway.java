import ch24_优先队列.IndexMinPQ;
import edu.princeton.cs.algs4.In;

//使用优先队列的多向归并
public class Multiway
{
	//将多行有序字符串归并为一行有序的字符串,无论输入多长都可以归并
	public static void merge( In[] streams )
	{
		int N = streams.length;
		IndexMinPQ<String> pq = new IndexMinPQ<String>( N );

		for( int i = 0; i < N; ++i )
			if( !streams[i].isEmpty() )
				pq.insert( i, streams[i].readString() );

		while( !pq.isEmpty() )
		{
			System.out.println(pq.minKey());
			
			int i = pq.delMin();
			
			if( !streams[i].isEmpty() )
				pq.insert( i, streams[i].readString() );
		}
	}
	
	public static void main( String[] args )
	{
		int N = args.length;
		In[] streams = new In[N];
		for(int i = 0; i < N; ++i)
			streams[i] = new In(args[i]);
		merge(streams);
	}
}
