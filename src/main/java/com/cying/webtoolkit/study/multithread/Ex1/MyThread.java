package com.cying.webtoolkit.study.multithread.Ex1;

public class MyThread extends Thread{

	@Override
	public void run() {
		super.run();
		for(int i = 0; i<500000; i++){
			System.out.println("i="+(i+1));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
