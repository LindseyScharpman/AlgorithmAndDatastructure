package ch24_优先队列;

public class ex2_4_15_isMaxHeap
{

	// 对a[1...a.length-1]判断是否是最大堆
	public static boolean isMaxHeap( Comparable[] a )
	{
		return isMaxHeap( a, 1, a.length - 1 );
	}

	//判断a[1...end]以parent作为根节点的堆是不是最大堆
	private static boolean isMaxHeap( Comparable[] a, int parent, int end )
	{
		//如果parent节点是叶子节点,那以此节点作为根节点的堆必然是最大堆
		if( parent * 2 > end )
			return true;

		int leftChild = parent * 2;
		int rightChild = parent * 2 + 1;
		//parent节点比左孩子节点小,则不是最大堆.
		//若存在右孩子节点,且parent节点比它小,则同样不是最大堆
		if( less( a[parent], a[leftChild] ) || ( rightChild <= end && less( a[parent], a[rightChild] ) ) )
			return false;

		boolean left = isMaxHeap( a, leftChild, end );
		boolean right = isMaxHeap( a, rightChild, end );
		return left && right;
	}

	private static boolean less( Comparable a, Comparable b )
	{
		return a.compareTo( b ) < 0;
	}

	public static void main( String[] args )
	{
		// 二叉堆,用树表示的话是完全二叉树,要么是叶子节点,要么有2个子节点
		String[] maxHeapCompleteBT = { "RESERVED", "T", "S", "R", "P", "N", "O", "A", "E", "I", "H", "G" };
		// 二叉堆,用树表示的话是非完全二叉树,要么是叶子节点,要么有2个子节点,要么有1个子节点
		String[] maxHeapNotCompleteBT = { "RESERVED", "T", "S", "R", "P", "N", "O", "A", "E", "I", "H" };

		System.out.println( isMaxHeap( maxHeapCompleteBT ) );
		System.out.println( isMaxHeap( maxHeapNotCompleteBT ) );
	}

}
