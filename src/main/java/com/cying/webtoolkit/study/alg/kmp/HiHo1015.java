package com.cying.webtoolkit.study.alg.kmp;

import java.util.Scanner;

public class HiHo1015 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		int[] matchNumArray = new int[num];
		for (int i = 0; i < num; i++) {
			String parttern = sc.next();
			String src = sc.next();
			matchNumArray[i] = countMatch(src, parttern);
		}
		for (int j = 0; j < num; j++) {
			System.out.println(matchNumArray[j]);
		}

	}

	private static int countMatch(String src, String parttern) {

		// 计算next,注意求出的next索引是从1开始的,第0个位置无意义
		int[] next = next2(parttern.toCharArray());
		// 起始比较位置
		int srcIndex = 0;
		int partternIndex = 0;
		int matchNum = 0;
		boolean matching =false; //是否处于已经有字符匹配的状态
		while (srcIndex < src.length()) {

			if (src.charAt(srcIndex) != parttern.charAt(partternIndex)) {

				if(!matching){
					srcIndex++;
				}
				// 如果next取值为0,此时必须从头开始匹配,即要重置模式索引
				if (next[partternIndex] == 0) {
					matching =false;
					partternIndex = 0;
				}else{
					partternIndex = next[partternIndex];
				}	
				continue;
			} else if (partternIndex+1 == parttern.length()) {
				matchNum++;
				srcIndex++;
				// 模式已经匹配完毕，重置
				if (next[partternIndex+1] == 0) {
					partternIndex = 0;
				}else{
					partternIndex = next[partternIndex+1];
				}
				matching=true;
				continue;
			}
			// 如果当前字符匹配,索引移动一个
			else {
				matching=true;
				srcIndex++;
				partternIndex++;
			}
		}
		return matchNum;
	}

	private static int[] next2(char[] parttern) {

		int[] next = new int[parttern.length + 1];
		next[0] = next[1] = 0;
		int j = 0;
		for (int i = 1; i < parttern.length; i++) {

			// 如果与当前值不匹配，一直往前找
			while (j > 0 && parttern[i] != parttern[j]) {
				j = next[j];
			}
			// 如果是找到了匹配的,而不是j变为0导致上面while退出
			if (parttern[i] == parttern[j]) {
				j++; // j由于是索引,而next需要的是长度，所以加1
			}
			// 写入值
			next[i + 1] = j;
		}
		return next;
	}
}
