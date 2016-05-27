package com.cying.webtoolkit.model;

import java.util.List;

import org.springframework.data.annotation.Id;

public class JarFile {
	   
	@Id
	private int jarFileId;
	private String jarFileRealName;
	private String jarFileStoreName;
	private List<String>classList;
	
	public String getJarFileRealName() {
		return jarFileRealName;
	}
	public void setJarFileRealName(String jarFileRealName) {
		this.jarFileRealName = jarFileRealName;
	}
	public List<String> getClassList() {
		return classList;
	}
	public void setClassList(List<String> classList) {
		this.classList = classList;
	}
	public String getJarFileStoreName() {
		return jarFileStoreName;
	}
	public void setJarFileStoreName(String jarFileStoreName) {
		this.jarFileStoreName = jarFileStoreName;
	}
	public int getJarFileId() {
		return jarFileId;
	}
	public void setJarFileId(int jarFileId) {
		this.jarFileId = jarFileId;
	}
}
