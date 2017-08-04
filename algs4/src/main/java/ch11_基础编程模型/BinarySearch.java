package ch11_基础编程模型;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdOut;

public class BinarySearch {

	// 数组必须有序,此处假设了是从低到高排列
	// 返回key的索引
	public static int rank(int key, int[] arr) {
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

	// level用于跟踪调用过程
	public static int rankR(int key, int[] arr, int lo, int hi, int level) {
		// 打印递归调用过程
		String preStr = "";
		for (int i = 0; i < level; ++i)
			preStr += "\t";
		StdOut.println(preStr + "lo: " + lo + " hi: " + hi);

		if (lo > hi) // 递归终止条件
			return -1;
		int mid = lo + (hi - lo) / 2;
		if (key < arr[mid])
			return rankR(key, arr, lo, mid - 1, level + 1);
		else if (key > arr[mid])
			return rankR(key, arr, mid + 1, hi, level + 1);
		else
			return mid;
	}

	public static void main(String[] args) {
		
		
		int[] whiteList = { 84, 48, 68, 10, 18, 98, 12, 23, 54, 57, 48, 33, 16,
				77, 11, 29 };
		int[] keys = { 23, 50, 10, 99, 18, 23, 98, 84, 11, 10, 48, 77, 13, 54,
				98, 77, 77, 68 };
		
		Arrays.sort(whiteList);
		// 打印出排序后的whiteList
		for (int i = 0; i < whiteList.length; ++i)
			StdOut.println(whiteList[i]);
		StdOut.println("===================");

		for(int key : keys) {
			// 读取键值，不在白名单内则打印出来
			// if(rank(key, whiteList) < 0)
			if (rankR(key, whiteList, 0, whiteList.length - 1, 0) < 0) // 递归方法进行二分查找
			{
				StdOut.println(key);
			}
		}
	}
}
