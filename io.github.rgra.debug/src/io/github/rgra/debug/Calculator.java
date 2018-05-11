package io.github.rgra.debug;

public class Calculator {

	public static void main(String[] args) {
		int a = 5;
		int b = 6;
		int x = 10;

		int result = a + b;
		result *= x;
		result += b;
		result -= a;

		String string = Integer.toHexString(result);

		System.out.println(string);
	}

}
