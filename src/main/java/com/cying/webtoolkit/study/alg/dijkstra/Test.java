/**
 * 
 */
package com.cying.webtoolkit.study.alg.dijkstra;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author cying
 *
 */
public class Test {
   
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for(int i=0; i<n; i++){
			a[i] = sc.nextInt();
		}
		//print(a);
		func(n, k, a);
		print(a);
	}
	
	
	static void func(int n, int k,int [] a){
		int f = a[0];
		int s = a[1];
		for(int i=0; i< k; i++){
			
			//第1次,先保存第一个数
			int  first = a[0];
			a[0] = (a[0]+a[1])%100;
			
			if((a[0] == f && a[1]==s) || (i==111)){
				f=a[0];
				f=a[1];
				System.out.println(i);
			}
			
			//第2到n-1次
			for(int j=1; j<n-1; j++){
				int newValue = a[j]+a[j+1];
				a[j] = newValue%100;
			}
			
			//第n次
			a[n-1] = (a[n-1]+first)%100;
			
		}
	}
  
	static void print(int[] a){
		//System.out.print("[");
		for(int i = 0; i<a.length; i++){
			System.out.print(a[i]+" ");
		}
		//System.out.println("]");
		
	}
}
