package com.cying.webtoolkit.study.multithread.Ex1;

public class Run2 {

	public static void main(String[] args) {

		try {
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
			//isInterrupted��������ж�״̬
			System.out.println("�Ƿ�ֹͣ1 �� ="+thread.isInterrupted());
			System.out.println("�Ƿ�ֹͣ2 �� ="+thread.isInterrupted());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}

}
