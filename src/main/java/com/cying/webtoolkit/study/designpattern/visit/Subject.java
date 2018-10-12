package com.cying.webtoolkit.study.designpattern.visit;

public interface Subject {
	public void accept(Visitor visitor);
	public String getSubject();
}
