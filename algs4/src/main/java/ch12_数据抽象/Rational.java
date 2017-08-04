package ch12_数据抽象;

import edu.princeton.cs.algs4.StdOut;

public class Rational
{
	private long numerator = 0; // 分子
	private long denominator = 0; // 分母

	public Rational()
	{
	}

	public Rational( int numerator, int denominator )
	{
		if( denominator == 0 )
			throw new RuntimeException( "denominator is zero!" );
		long r = gcd( numerator, denominator );
		this.numerator = numerator / r;
		this.denominator = denominator / r;
	}

	// 求p,q的最大公约数,p和q顺序任意
	public static long gcd( long p, long q )
	{
		if( q == 0 )
			return p;
		if( p == 0 )
			return q;
		long r = p % q;
		return gcd( q, r );
	}

	public Rational plus( Rational b )
	{
		Rational rs = new Rational();
		long denominator = this.denominator * b.denominator;
		long numerator = this.numerator * b.denominator + this.denominator * b.numerator;

		long r = gcd( denominator, numerator );
		rs.denominator = denominator / r;
		rs.numerator = numerator / r;
		return rs;
	}

	public Rational minus( Rational b )
	{
		Rational rs = new Rational();
		long denominator = this.denominator * b.denominator;
		long numerator = this.numerator * b.denominator - this.denominator * b.numerator;

		long r = gcd( denominator, numerator );
		rs.denominator = denominator / r;
		rs.numerator = numerator / r;
		return rs;
	}

	public Rational times( Rational b )
	{
		Rational rs = new Rational();
		long denominator = this.denominator * b.denominator;
		long numerator = this.numerator * b.numerator;

		long r = gcd( denominator, numerator );
		rs.denominator = denominator / r;
		rs.numerator = numerator / r;
		return rs;
	}

	public Rational divides( Rational b )
	{
		Rational rs = new Rational();
		long denominator = this.denominator * b.numerator;
		long numerator = this.numerator * b.denominator;

		long r = gcd( denominator, numerator );
		rs.denominator = denominator / r;
		rs.numerator = numerator / r;
		return rs;
	}

	public boolean equals( Rational that )
	{
		return (this.denominator == that.denominator) && (this.numerator == that.numerator);
	}

	public String toString()
	{
		if( this.numerator == this.denominator )
			return "1";
		else if( this.numerator > 0 && this.denominator > 0 )
			return this.numerator + "/" + this.denominator;
		else if( this.numerator < 0 && this.denominator < 0 )
			return this.numerator + "/" + this.denominator;
		else if( this.numerator > 0 && this.denominator < 0 )
			return "-" + this.numerator + "/" + Math.abs( this.denominator );
		else if( this.numerator < 0 && this.denominator > 0 )
			return "-" + Math.abs( this.numerator ) + "/" + this.denominator;
		else if( this.numerator == 0 || this.denominator == 0 )
			return "0";
		else
			return "unknown error";
	}

	public static void main( String[] args )
	{
		Rational x, y, z;

		// 1/2 + 1/3 = 5/6
		x = new Rational( 1, 2 );
		y = new Rational( 1, 3 );
		z = x.plus( y );
		StdOut.println( z );

		// 8/9 + 1/9 = 1
		x = new Rational( 8, 9 );
		y = new Rational( 1, 9 );
		z = x.plus( y );
		StdOut.println( z );

		// 1/200000000 + 1/300000000 = 1/120000000
		x = new Rational( 1, 200000000 );
		y = new Rational( 1, 300000000 );
		z = x.plus( y );
		StdOut.println( z );

		// 1073741789/20 + 1073741789/30 = 1073741789/12
		x = new Rational( 1073741789, 20 );
		y = new Rational( 1073741789, 30 );
		z = x.plus( y );
		StdOut.println( z );

		// 4/17 * 17/4 = 1
		x = new Rational( 4, 17 );
		y = new Rational( 17, 4 );
		z = x.times( y );
		StdOut.println( z );

		// 3037141/3247033 * 3037547/3246599 = 841/961
		x = new Rational( 3037141, 3247033 );
		y = new Rational( 3037547, 3246599 );
		z = x.times( y );
		StdOut.println( z );

		// 1/6 - -4/-8 = -1/3
		x = new Rational( 1, 6 );
		y = new Rational( -4, -8 );
		z = x.minus( y );
		StdOut.println( z );
	}
}