package ch11_基础编程模型.ex;

public class ex20_LnN {
	
	// 递归计算Ln(n!)
	public static double logFactN(int n) {
		if (n == 1)
			return 0;
		else
			return Math.log((double) n) + logFactN(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(logFactN(4));
	}
}