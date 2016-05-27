package com.cying.webtoolkit.study.multithread.Ex1;

public class Run {

	public static void main(String[] args) {
		
		try{
			MyThread thread = new MyThread();
			thread.start();
			Thread.sleep(1000);
			thread.interrupt();
			System.out.println("ÊÇ·ñÍ£Ö¹1 £¿ ="+Thread.currentThread().interrupted());
			System.out.println("ÊÇ·ñÍ£Ö¹2 £¿ ="+Thread.currentThread().interrupted());
		}catch(InterruptedException e){
			System.out.println("main catch");
			e.printStackTrace();
		}

	}

}
