package ch12_数据抽象;

// 旋转字符串,比如说abcdefg,左旋转3位変成defgabc
public class ex_Rotate
{
	public static void main( String[] args )
	{
		char[] s = { 'a', 'b', 'c', 'd', 'e', 'f', 'g' };
		R( s, 3 );
		for( int i = 0; i < s.length; i++ )
			System.out.print( s[i] );
	}

	// 注意旋转字符串后的字符串和原字符串其实构成一个交换群
	public static void R( char[] s, int i )
	{
		// 方法1:
		// reverse( s, 0, i - 1 );
		// reverse( s, i, s.length - 1 );
		// reverse( s, 0, s.length - 1 );

		// 方法2
		reverse( s, 0, s.length - 1 );
		reverse( s, 0, s.length - 1 - i );
		reverse( s, s.length - i, s.length - 1 );

	}

	private static void reverse( char[] s, int begin, int end )
	{
		int len = end - begin + 1;
		for( int i = begin; i < begin + len / 2; i++, --end )
		{
			char c = s[i];
			s[i] = s[end];
			s[end] = c;
		}
	}
}
