package com.cying.webtoolkit.study.multithread.Ex1;

public class Run2 {

	public static void main(String[] args) {

		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
			//isInterrupted不会清除中断状态
			System.out.println("是否停止1 ？ ="+thread.isInterrupted());
			System.out.println("是否停止2 ？ ="+thread.isInterrupted());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
