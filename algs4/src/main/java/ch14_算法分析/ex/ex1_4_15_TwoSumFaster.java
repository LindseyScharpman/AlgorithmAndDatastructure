package ch14_算法分析.ex;

/* 线性时间解决2-sum,前提数组已经排序好
 * 对于3-sum,可以在平方级别内解决,前提数组排序好
 */
public class ex1_4_15_TwoSumFaster 
{
	//统计已经升序排序好的数组中和为0的整数对的数量,假设没有重复元素
	public static int count(int arr[])
	{
		/* go从数组开始往前扫描,back从数组末尾往前扫描
		 * 如果arr[go] + arr[back] < 0,说明没有和arr[go]相加等于0的整数,则go往前走一步
		 * 如果arr[go] + arr[back] > 0,说明没有和arr[back]相加等于0的整数,则back往后走一步
		 * 如果相加等于0,则统计量count加1,且go和back均各走一步
		 * 直到go超过或者等于back,说明已经扫描完了数组,退出循环,否则会多计算1倍整数对的数量
		 */
		int go = 0, back = arr.length - 1;
		int count = 0;
		for(int i = 0; i < arr.length; ++i)
		{
			if(go >= back)
				break;
			if(arr[go] + arr[back] < 0)
			{
				++go;
			}
			else if(arr[go] + arr[back] > 0)
			{
				--back;
			}
			else
			{
				--back;
				++go;
				++count;
			}
		}
		return count;
	}
	
	public static void main(String[] args) 
	{
		int[] arr = {-9, -7, -6, -5, -3, -2, -1, 1, 2, 6};
		System.out.println(count(arr));
	}

}
