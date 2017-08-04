package ch11_基础编程模型.ex;

import java.util.Arrays;

public class ex14_Log2N
{
	// 计算不大于Log2(N)的整数
	// 2^m =< N
	// 方法1:不断乘以2,并判断是否到达N,统计由多个个2相乘
	// 方法2:计算N的二进制表示的最高位为1所在的位置,例如Log2(3),3的二进制为011,所在位置为bit1,返回此位置的值即可
	public static int log2N( int n )
	{
		int m = 0;
		int product = 2;
		while( product <= n )
		{
			product *= 2;
			++m;
		}
		return m;
	}

	public static int log2N2( int n )
	{
		if( n <= 0 )
			return 0;

		int i = 0;
		for( i = 0; n > 0; ++i, n >>= 1 )
			;
		return i - 1;
	}

	public static void main( String[] args )
	{

		System.out.println( log2N( 1 ) == log2N2( 1 ) );
		System.out.println( log2N( 2 ) == log2N2( 2 ) );
		System.out.println( log2N( 3 ) == log2N2( 3 ) );
		System.out.println( log2N( 4 ) == log2N2( 4 ) );
		System.out.println( log2N( 5 ) == log2N2( 5 ) );
		System.out.println( log2N( 6 ) == log2N2( 6 ) );
		System.out.println( log2N( 7 ) == log2N2( 7 ) );
		System.out.println( log2N( 8 ) == log2N2( 8 ) );
	}
}