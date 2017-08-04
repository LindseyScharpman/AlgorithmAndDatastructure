package ch11_基础编程模型.ex;

import edu.princeton.cs.algs4.StdOut;

// 常用的二分搜索
public class ex29_BinarySearchIncludeEqualKey {

	// 对于数k1,k2,如果向下取整(k1+k2)/2, 则向着 "<---" 方向走
	// 对于数k1,k2,如果向上取整(k1+k2+1)/2,则向着 "--->" 方向走

	// 在arr里面找到比key刚好大的元素的索引
	// 如果比key刚好大的元素有很多个,则返回最左的那一个
	public static int strictUpperBound(int key, int arr[]) {
		// 如果key >= MAX( arr[] ),则arr里面没有比key还大的元素
		if (key >= arr[arr.length - 1])
			return -1;

		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while (lo < hi) {
			mid = (hi + lo) / 2;
			if (arr[mid] > key)
				hi = mid;
			else
				lo = mid + 1;
		}

		// while退出时 lo == hi,此时该位置上的元素就是刚好大于key的元素
		return hi; // 也可以return lo;
	}

	// 在arr里面找到比key刚好小的元素的索引
	// 如果比key刚好小的元素有很多个,则返回最右的那一个
	public static int strictLowerBound(int key, int arr[]) {
		if (key <= arr[0])
			return -1;

		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while (lo < hi) {
			mid = (hi + lo + 1) / 2;
			if (arr[mid] < key)
				lo = mid;
			else
				hi = mid - 1;
		}

		return hi; // 也可以直接return lo;
	}

	// 在arr里面寻找刚好大于等于key的元素,如果存在,返回此元素的索引
	// 若存在多个刚好大于等于key的元素,则返回最左的元素的索引
	public static int UpperBound(int key, int arr[]) {
		if (key > arr[arr.length - 1])
			return -1;

		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while (lo < hi) {
			mid = (hi + lo) / 2;
			if (arr[mid] >= key)
				hi = mid;
			else
				lo = mid + 1;
		}

		return hi; // 也可以直接return lo;
	}

	// 在arr里面找到小于等于key的元素的索引
	// 如果小于等于key的元素有很多个,则返回最右的那一个
	public static int LowerBound(int key, int arr[]) {
		if (key < arr[0])
			return -1;

		int lo = 0, hi = arr.length - 1;
		int mid = 0;
		while (lo < hi) {
			mid = (hi + lo + 1) / 2;
			if (arr[mid] <= key)
				lo = mid;
			else
				hi = mid - 1;
		}

		return hi; // 也可以直接return lo;
	}

	// 习题29
	// 返回数组中小于key的数量
	public static int rank(int key, int arr[]) {
		int slb = strictLowerBound(key, arr);
		if (slb == -1)
			return 0;
		return slb + 1;
	}

	// 返回数组中等于key的数量,可以自己画个图理解一下
	// 嘿嘿,得意之作,省略了许多的判断,真佩服自己的灵感,求测试不通过,哼!
	// 巧妙的利用了binarysearch/LowerBound/UpperBound,没有选择用strictLowerBound
	// 一旦用了strictLowerBound你会发现要处理许多返回-1的情况哟~
	public static int count(int key, int arr[]) {
		if (binarysearch(key, arr) == -1)
			return 0;
		int lb = LowerBound(key, arr);
		int ub = UpperBound(key, arr);
		return lb - ub + 1;
	}

	public static int binarysearch(int key, int[] arr) {
		int lo = 0;
		int hi = arr.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (key < arr[mid])
				hi = mid - 1;
			else if (key > arr[mid])
				lo = mid + 1;
			else
				return mid;
		}
		return -1;
	}

	public static void main(String[] args) {
		int a[] = { 1, 2, 3, 3, 4, 5, 6, 7, 8 };

		StdOut.println(strictUpperBound(3, a));
		StdOut.println(strictLowerBound(3, a));

		StdOut.println(UpperBound(3, a));
		StdOut.println(LowerBound(3, a));

		StdOut.println("==============");
		StdOut.println(rank(3, a));
		StdOut.println(count(3, a));
	}
}
