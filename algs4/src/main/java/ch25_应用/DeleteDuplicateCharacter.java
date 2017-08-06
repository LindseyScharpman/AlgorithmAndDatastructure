package ch25_应用;

public class DeleteDuplicateCharacter
{

	// 暴力方法删除字符串中的重复字符.O(N^2)
	public static StringBuilder deleteDupChar( StringBuilder s )
	{
		int len = s.length();
		int go = 0;

		// 每扫描一个字符,就检查它后面是否有和当前字符重复字符,如果有,则设为'\0'
		// 下一次遇到'\0'时则跳过此字符
		for( int i = 0; i < len; ++i )
		{
			if( s.charAt( i ) != '\0' )
			{
				s.setCharAt( go++, s.charAt( i ) );
				for( int j = i + 1; j < len; ++j )
					if( s.charAt( j ) == s.charAt( i ) )
						s.setCharAt( j, '\0' );
			}
		}
		return new StringBuilder( s.subSequence( 0, go ) );
	}

	// 用一个数组来标记是否出现过,这里假设字符串是a-z组成
	// 当然也可以用个int变量的位来标记
	public static StringBuilder deleteDupChar2( StringBuilder s )
	{
		boolean[] az = new boolean[26];
		int len = s.length();
		int go = 0;

		// 每扫描一个字符,就检查它是否出现过,如果没有出现,则设置为出现过
		// 下一次遇到该字符时则跳过此字符
		for( int i = 0; i < len; ++i )
		{
			if( az[s.charAt( i ) - 'a'] == false )
			{
				s.setCharAt( go++, s.charAt( i ) );
				az[s.charAt( i ) - 'a'] = true;
			}
		}
		return new StringBuilder( s.subSequence( 0, go ) );
	}

	public static void main( String[] args )
	{
		System.out.println( deleteDupChar( new StringBuilder( "abcccab" ) ) );
		System.out.println( deleteDupChar2( new StringBuilder( "babcc" ) ) );
	}

}
