package ch22_归并排序;

import static org.junit.Assert.*;

// 计算逆序对的个数
public class CountInversions
{
    private static int[] aux = null;

    public static void main( String[] args )
    {
        int[] A = {2,3,8,6,1}; //5个逆序对
        assertEquals( 5, countInversions( A ) );

        int []A2 = {5,7,1,2,3,6,0}; //13个逆序对
        assertEquals( 13, countInversions( A2 ) );

        for( int i = 0; i < A2.length; i++ )
        {
            System.out.print( A2[i] + " " );
        }
    }

    public static int countInversions( int[] A )
    {
        aux = new int[A.length];
        return countInversions( A, 0, A.length - 1 );
    }

    // A[lo...hi]的逆序对
    private static int countInversions( int[] A, int lo, int hi )
    {
        int cnt = 0;
        if( lo < hi )
        {
            int mid = ( lo + hi ) / 2;
            cnt += countInversions( A, lo, mid );
            cnt += countInversions( A, mid + 1, hi );
            cnt += merge( A, lo, mid, hi );
        }
        return cnt;
    }

    // 归并A[lo...mid]和A[mid+1...hi]
    private static int merge( int[] A, int lo, int mid, int hi )
    {
        assert lo < hi;

        int i = lo;
        int j = mid + 1;
        int cnt = 0;

        for( int k = lo; k <= hi; k++ )
            aux[k] = A[k];

        // 归并
        for( int k = lo; k <= hi; k++ )
        {
            if( i > mid )
            {
                A[k] = aux[j++];
            }
            else if( j > hi )
            {
                A[k] = aux[i++];
            }
            else if( less( aux[i], aux[j] ) )
            {
                A[k] = aux[i++];
            }
            else
            {
                A[k] = aux[j++];
                // 只要当前左边数组的数大于右边数组的数,那么左边的数组中后续的数必然大于当前右边数组的数
                cnt += mid - i + 1;
            }
        }

        return cnt;
    }

    private static boolean less( int i, int j )
    {
        return i < j;
    }
}
