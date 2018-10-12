package com.cying.webtoolkit.study.designpattern.bridge;

public class MyBridge extends Bridge {
	public void method(){
		getSource().method();
	}
}
