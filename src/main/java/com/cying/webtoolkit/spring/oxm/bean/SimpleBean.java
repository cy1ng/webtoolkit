package com.cying.webtoolkit.spring.oxm.bean;

public class SimpleBean {
   
	private int age;
	private boolean executive;
	private String jobDescription;
	private String name;
	
	public int getAge() {
		return age;
	}
	public boolean isExecutive() {
		return executive;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setExecutive(boolean executive) {
		this.executive = executive;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public void setName(String name) {
		this.name = name;
	}
}
