package com.velocity;

public class Demo {
	public static void main(String[] args) {
		int no =153;
		int reverse=0;
		while(no!=0) {
			reverse=reverse*10;
			reverse=reverse+no%10;
			no=no/10;
		}System.out.println(reverse);
	}

}
