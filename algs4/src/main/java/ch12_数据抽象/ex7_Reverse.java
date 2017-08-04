package ch12_数据抽象;

public class ex7_Reverse
{
	// 反转字符串
	public static void main( String[] args )
	{

//		
//		System.out.println( reverse1( "O" ).equals( reverse2( "O" ) ) );
//		System.out.println( reverse1( "OK" ).equals( reverse2( "OK" ) ) );
//		System.out.println( reverse1( "ABC" ).equals( reverse2( "ABC" ) ) );
//
//		System.out.println( reverse1( "O" ).equals( reverse3( "O" ) ) );
//		System.out.println( reverse1( "OK" ).equals( reverse3( "OK" ) ) );
//		System.out.println( reverse1( "ABC" ).equals( reverse3( "ABC" ) ) );
	}

	// O(N^2)
	public static String reverse1( String s )
	{
		if( s == null || s.length() == 1 )
			return s;
		return reverse1( s.substring( 1 ) ) + s.charAt( 0 );
	}

	// O(NlgN)
	public static String reverse2( String s )
	{
		if( s == null )
			return s;
		int len = s.length();
		if( len <= 1 )
			return s;
		String a = s.substring( 0, len / 2 );
		String b = s.substring( len / 2, len );
		return reverse2( b ) + reverse2( a );
	}

	// O(N)
	public static String reverse3( String s )
	{
		int len = s.length() - 1;
		char[] chars = s.toCharArray();
		for( int i = 0; i < len; i++, --len )
		{
			chars[i] ^= chars[len];
			chars[len] ^= chars[i];
			chars[i] ^= chars[len];
		}
		return new String( chars );
	}


}
