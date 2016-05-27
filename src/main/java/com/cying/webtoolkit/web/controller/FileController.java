package com.cying.webtoolkit.web.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public void upload(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException{
		logger.debug("文件解析开始：");
		if(ServletFileUpload.isMultipartContent(req)){
			logger.debug("报文是多部分的,确为文件上传！");			
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// Configure a repository (to ensure a secure temp location is used)
			File repository = new File("D:\\data");
			factory.setRepository(repository);

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);

			// Parse the request
			List<FileItem> items = upload.parseRequest(req);
			logger.debug("已上传列表：");
			for(FileItem item : items){
				System.out.println(item.getName());
			}
		}
		
		
	}
}
