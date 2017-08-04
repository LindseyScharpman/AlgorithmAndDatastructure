package ch12_数据抽象;

import java.util.*;
import edu.princeton.cs.algs4.StdOut;

public class Transaction
{

	private String who = null;
	private Date when = null;
	private double amount = 0;

	public Transaction( String who, Date when, double amount )
	{
		if( Double.isNaN( amount ) || Double.isInfinite( amount ) )
			throw new IllegalArgumentException( "NaN or infinite" );
		this.who = who;
		this.when = when;
		this.amount = amount;
	}

	public Transaction( String transaction )
	{
		String[] a = transaction.split( "\\s+" ); // 以空白字符为分割
		who = a[0];
		when = new Date( a[1] );
		double value = Double.parseDouble( a[2] );
		if( value == 0.0 )
			amount = 0.0; // convert -0.0 0.0
		else
			amount = value;
		if( Double.isNaN( amount ) || Double.isInfinite( amount ) )
			throw new IllegalArgumentException( "NaN or infinite" );
	}

	public String who()
	{
		return this.who;
	}

	public Date when()
	{
		return this.when;
	}

	public String toString()
	{
		return String.format( "%-10s %10s %8.2f", who, when, amount );
	}

	public boolean equals( Object that )
	{
		if( this == that )
			return true;
		if( that == null )
			return false;
		if( this.getClass() != that.getClass() )
			return false;

		Transaction thatO = (Transaction) that;
		if( !this.who.equals( thatO.who ) )
			return false;
		if( !this.when.equals( thatO.when ) )
			return false;
		if( this.amount != thatO.amount )
			return false;
		return true;
	}

	public int hashCode()
	{
		int hash = 17;
		hash = 31 * hash + who.hashCode();
		hash = 31 * hash + when.hashCode();
		hash = 31 * hash + ( (Double) amount ).hashCode();
		return hash;
	}

	public int compareTo( Transaction that )
	{
		if( this.amount < that.amount )
			return -1;
		else if( this.amount > that.amount )
			return 1;
		else
			return 0;
	}

	public static class WhoOrder implements Comparator<Transaction>
	{
		public int compare( Transaction s1, Transaction s2 )
		{
			return s1.who.compareTo( s2.who );
		}
	}

	public static class WhenOrder implements Comparator<Transaction>
	{
		public int compare( Transaction s1, Transaction s2 )
		{
			return s1.when.compareTo( s2.when );
		}
	}

	public static class HowMuchOrder implements Comparator<Transaction>
	{
		public int compare( Transaction s1, Transaction s2 )
		{
			return s1.compareTo( s2 );
		}
	}

	public static void main( String[] args )
	{
		Transaction[] a = new Transaction[4];
		a[0] = new Transaction( "Turing   6/17/1990  644.08" );
		a[1] = new Transaction( "Tarjan   3/26/2002 4121.85" );
		a[2] = new Transaction( "Knuth    6/14/1999  288.34" );
		a[3] = new Transaction( "Dijkstra 8/22/2007 2678.40" );

		StdOut.println( "Unsorted" );
		for( int i = 0; i < a.length; i++ )
			StdOut.println( a[i] );
		StdOut.println();

		StdOut.println( "Sort by date" );
		Arrays.sort( a, new WhenOrder() );
		for( int i = 0; i < a.length; i++ )
			StdOut.println( a[i] );
		StdOut.println();

		StdOut.println( "Sort by customer" );
		Arrays.sort( a, new WhoOrder() );
		for( int i = 0; i < a.length; i++ )
			StdOut.println( a[i] );
		StdOut.println();

		StdOut.println( "Sort by amount" );
		Arrays.sort( a, new HowMuchOrder() );
		for( int i = 0; i < a.length; i++ )
			StdOut.println( a[i] );
		StdOut.println();
	}
}