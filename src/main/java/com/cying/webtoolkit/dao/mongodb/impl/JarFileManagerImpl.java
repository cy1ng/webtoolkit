package com.cying.webtoolkit.dao.mongodb.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cying.webtoolkit.dao.mongodb.interfaces.JarFileManager;
import com.cying.webtoolkit.model.JarFile;

//@Service("jarFileManager")
public class JarFileManagerImpl implements JarFileManager {
	
//    @Autowired
//	private MongoTemplate mongoTemplate;
	
	@Override
	public boolean add(JarFile jarFile) {
//		mongoTemplate.insert(jarFile);
		return true;
	}

	@Override
	public boolean addClassToJarFile(String jarFileRealName, String fullClassName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JarFile findJarByClassName(String fullClassName) {
		// TODO Auto-generated method stub
		return null;
	}



}
