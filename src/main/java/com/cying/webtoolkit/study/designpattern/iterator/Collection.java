package com.cying.webtoolkit.study.designpattern.iterator;

public interface Collection {
	
	public Iterator iterator();
	
	/*ȡ�ü���Ԫ��*/
	public Object get(int i);
	
	/*ȡ�ü��ϴ�С*/
	public int size();
}
