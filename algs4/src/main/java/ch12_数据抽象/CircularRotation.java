package ch12_数据抽象;

public class CircularRotation
{
	// 最简单的方法判断是否是回环变位
	public static boolean isCircularRotationBest( String s1, String s2 )
	{
		return ( s1.length() == s2.length() ) && ( s1.concat( s1 ).indexOf( s2 ) >= 0 );
	}

	public static boolean isCircularRotation( String s1, String s2 )
	{
		if( s1.length() != s2.length() )
			return false;

		int len = s1.length();
		for( int i = 0; i < len; ++i )
		{
			String preStr = s1.substring( 0, i + 1 ); // 拿到s1[0]--s1[i]不包括s1[i+1]
			String nextStr = s1.substring( i + 1, len );

			String newS1 = nextStr + preStr;
			if( newS1.equals( s2 ) )
				return true;
		}
		return false;
	}

	public static void main( String[] args )
	{
		System.out.println( isCircularRotationBest( "ACTGACG", "TGACGAC" ) );
	}
}