package io.github.rgra.debug;

public class Calculator {

	public static void main(String[] args) {
		int a = 5;
		int b = 6;
		int x = 10;

		int result1 = a + b;
		int result2 = result1 * x;
		int result3 = result2 + b;
		int result4 = result3 - a;

		String string = Integer.toHexString(result4);

		System.out.println(string);
	}

}
