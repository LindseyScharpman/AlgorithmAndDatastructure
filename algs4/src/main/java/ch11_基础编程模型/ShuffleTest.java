package ch11_基础编程模型;

import java.util.Arrays;
import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ShuffleTest
{

	private static Random random; // pseudo-random number generator
	private static long seed; // pseudo-random number generator seed

	// static initializer
	static
	{
		// this is how the seed was set in Java 1.4
		seed = System.currentTimeMillis();
		random = new Random( seed );
	}

	public static int uniform( int n )
	{
		if( n <= 0 )
			throw new IllegalArgumentException( "Parameter N must be positive" );
		return random.nextInt( n );
	}

	public static void shuffle( int[] a )
	{
		if( a == null )
			throw new NullPointerException( "argument array is null" );
		int n = a.length;
		for( int i = 0; i < n; i++ )
		{
			int r = uniform( n ); // between 0 and n-1
			int temp = a[i];
			a[i] = a[r];
			a[r] = temp;
		}
	}

	public static void main( String[] args )
	{
		int M = 33; // 大小为M的一维数组
		int N = 3300000; // 随机打乱N次
		int[] a = new int[M];
		int[][] p = new int[M][M]; // 如果均匀,里面的值应该在N/M附近

		for( int i = 0; i < N; ++i )
		{
			for( int j = 0; j < M; ++j )
				a[j] = j;
			StdRandom.shuffle( a );
			// ShuffleTest.shuffle(a);
			for( int j = 0; j < M; ++j )
			{
				for( int k = 0; k < M; ++k )
				{
					if( a[k] == j )
					{
						p[j][k] += 1;
					}
				}

			}
		}

		// 打印结果
		for( int i = 0; i < M; ++i )
		{
			for( int j = 0; j < M; ++j )
			{
				StdOut.print( "" + p[i][j] + "\t" );
			}
			StdOut.println();
		}
		
		
	}
}
