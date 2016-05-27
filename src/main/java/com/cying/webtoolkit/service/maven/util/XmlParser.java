package com.cying.webtoolkit.service.maven.util;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlParser {

	public static String parse(String xml) {

		Reader stringReader = new StringReader(xml);
		SAXReader reader = new SAXReader();
		Document doc;
		try {
			doc = reader.read(stringReader);
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		String result ="";
		return result;

	}
}
