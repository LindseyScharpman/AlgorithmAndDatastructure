import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickNoRecursive
{
    public static void sort( Comparable[] a )
    {
        StdRandom.shuffle( a );
        sort( a, 0, a.length - 1 );
    }

    // 快速排序,升序
    // 时间：O(NlogN),空间：logN
    public static void sort( Comparable[] a, int lo, int hi )
    {
        if( lo >= hi )
            return;

        Stack<Info> s = new Stack<Info>();

        int j = partition( a, lo, hi ); // 把数组分区
        int leftSize = (j - 1) - lo + 1; // 左边子数组大小
        int rightSize = hi - (j + 1) - 1; // 右边子数组大小
        //保证把大的子数组先压入栈,这样栈最多只有lgN个元素
        if( leftSize < rightSize )
        {
            s.push( new Info( j + 1, hi ) );
            s.push( new Info( lo, j - 1 ) );
        }
        else
        {
            s.push( new Info( lo, j - 1 ) );
            s.push( new Info( j + 1, hi ) );
        }

        while( !s.isEmpty() )
        {
            Info info = s.pop();
            System.out.println( "info.lo, info.hi:" + info.lo + "\t" + info.hi );
            if( info.hi - info.lo >= 1 )
            {
                j = partition( a, info.lo, info.hi );
                leftSize = (j - 1) - info.lo + 1;// 左边子数组大小
                rightSize = info.hi - (j + 1) - 1;// 右边子数组大小
                //保证把大的子数组先压入栈,这样栈最多只有lgN个元素
                if( leftSize < rightSize )
                {
                    s.push( new Info( j + 1, info.hi ) );
                    s.push( new Info( info.lo, j - 1 ) );
                }
                else
                {
                    s.push( new Info( info.lo, j - 1 ) );
                    s.push( new Info( j + 1, info.hi ) );
                }
            }
        }
    }

    private static class Info
    {
        int lo, hi;

        public Info( int lo, int hi )
        {
            this.lo = lo;
            this.hi = hi;
        }
    }

    // 此分区方法对于只有几种重复元素的效果很差,因此建议采用三向切分
    public static int partition( Comparable[] a, int lo, int hi )
    {
        // 以key = a[lo]为参考,a的左边都小于key,右边都大于等于key
        int keyIndex = lo;
        Comparable key = a[keyIndex];

        for( int i = lo + 1; i <= hi; ++i )
        {
            if( less( a[i], key ) )
                swap( a, ++keyIndex, i );
        }
        // 把key放在正确的位置上
        swap( a, keyIndex, lo );
        return keyIndex;
    }

    // v < w 返回true,否则返回false
    private static boolean less( Comparable v, Comparable w )
    {
        return v.compareTo( w ) < 0;
    }

    // 交换a[i] a[j]
    private static void swap( Object[] a, int i, int j )
    {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    // 打印数组a
    private static void show( Comparable[] a )
    {
        int len = a.length;
        for( int i = 0; i < len; ++i )
            StdOut.print( a[i] + " " );
        StdOut.println();
    }

    // 判断数组a是否为升序
    public static boolean isSorted( Comparable[] a )
    {
        int len = a.length;
        for( int i = 1; i < len; ++i )
            if( less( a[i], a[i - 1] ) )
                return false;
        return true;
    }

    public static void main( String[] args )
    {
        int N = 33;
        Integer[] a = new Integer[N];
        for( int i = 0; i < N; ++i )
            a[i] = StdRandom.uniform( 100 );

        sort( a );
        System.out.println( isSorted( a ) );
        show( a );
    }
}
