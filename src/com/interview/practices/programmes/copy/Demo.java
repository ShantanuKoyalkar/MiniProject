package com.interview.practices.programmes.copy;

public class Demo {
    
	public static void main(String[] args) {
	int count=5;
	int a=0;
	int b=1;
	int c=1;
	for(int i=0;i<=count;i++) {
	System.out.print(a+",");
	a=b;
	b=c;
	c=a+b;}
	}

}
