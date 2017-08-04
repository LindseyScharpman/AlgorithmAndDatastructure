package ch14_算法分析.ex;

//练习1.4.12
//线性时间内找出两个有序数组的所有公共元素
public class FindCommonElement 
{
	/* 遍历两个数组
	 * a[i] > b[j], j++
	 * a[i] < b[j], i++
	 * a[i] == b[j],打印出公共元素
	 * 直到a或者b的中元素遍历完
	 */
	public static void main(String[] args) 
	{
		int[] a = {1,2,3,4,5,6,7,99};
		int[] b = {-2,-2,9,8,10,99};
		
		int i = 0, j = 0;
		int lenA = a.length, lenB = b.length;
		while(i < lenA && j < lenB )
		{
			if(a[i] > b[j])
				++j;
			else if(a[i] < b[j])
				++i;
			else if(a[i] == b[j])
			{
				System.out.println(a[i] + "\t");
				++i; ++j;
			}
		}
	}

}
