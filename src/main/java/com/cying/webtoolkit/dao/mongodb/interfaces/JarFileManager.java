package com.cying.webtoolkit.dao.mongodb.interfaces;

import com.cying.webtoolkit.model.JarFile;

public interface JarFileManager {
	  
	/*
	 * 增加操作
	 */
	/*增加一条JarFile记录*/
	boolean add(JarFile jarFile);
	/*增加某个类名到一个JarFile记录中*/
	boolean addClassToJarFile(String jarFileRealName, String fullClassName);
	
	/*
	 * 删除
	 */
	
	/*
	 * 修改
	 */
	
	/*
	 *查询 
	 */
	/*通过类名称查询jar文件*/
	JarFile findJarByClassName(String fullClassName);
}