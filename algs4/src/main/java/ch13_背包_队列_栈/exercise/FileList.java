package ch13_背包_队列_栈.exercise;

import java.io.File;

//练习1.3.43
public class FileList
{
	// 打印该文件夹下面的所有文件
	public static void tree( String pathname, int level )
	{
		File dir = new File( pathname );
		File[] files = dir.listFiles();

		String preStr = "";
		for( int i = 0; i < level; ++i )
			preStr += "\t";

		for( int i = 0; i < files.length; ++i )
		{
			System.out.println( preStr + files[i].getName() );
			if( files[i].isDirectory() )
				tree( files[i].getAbsolutePath(), level + 1 );
		}
	}

	public static void main( String[] args )
	{
		tree( ".", 0 );
	}

}
