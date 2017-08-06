package ch25_应用;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Quick;

public class DeleteDuplicateString
{
	// 删除字符串数组s中的重复字符串
	public static String[] dedup( String[] s )
	{
		List<String> result = new ArrayList<String>();

		Quick.sort( s );
		result.add( s[0] );

		for( int i = 1; i < s.length; ++i )
		{
			if( s[i].compareTo( s[i - 1] ) != 0 ) // 跳过重复元素
				result.add( s[i] );
		}

		String[] r = new String[result.size()];
		int i = 0;
		for( String str : result )
			r[i++] = str;
		return r;
	}

	public static void main( String[] args )
	{
		String[] s = { "a", "b", "c", "b", "aa", "bb", "ax" };

		String a[] = dedup( s );
		for( int i = 0; i < a.length; ++i )
			System.out.println( a[i] );
	}

}
