package com.cying.webtoolkit.fastdfs.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import com.cying.webtoolkit.fastdfs.common.NameValuePair;
import com.cying.webtoolkit.fastdfs.config.ClientConfig;
import com.cying.webtoolkit.fastdfs.config.ClientConfig.Builder;
import com.cying.webtoolkit.fastdfs.storage.StorageClient;
import com.cying.webtoolkit.fastdfs.storage.StorageServer;
import com.cying.webtoolkit.fastdfs.tracker.TrackerClient;
import com.cying.webtoolkit.fastdfs.tracker.TrackerGroup;
import com.cying.webtoolkit.fastdfs.tracker.TrackerServer;

/*
 * FAST DFS 连接客户端
 */
public class DfsClient {
 
private static ClientConfig config;

static{
	
	 Builder builder = ClientConfig.custom();
	 InetSocketAddress addr1 = new InetSocketAddress("www.cying.link",22122);
	 TrackerGroup trackerGroup = new TrackerGroup(new InetSocketAddress[]{addr1});
	 config = builder.setNetworkTimeout(2000)
	         .setCharset("UTF-8")
	         .setTrackerHttpPort(8080)
	         .setAntiStealToken(false)
	         .setSecretKey("FastDFS1234567890")
	         .setTrackerGroup(trackerGroup)
	         .build();
	 trackerGroup.setConfig(config);
	
}

public ClientConfig getConfig() {
	return config;
}

public void setConfig(ClientConfig config) {
	this.config = config;
}

 public static void main(String[] args) throws Exception{
	 //DfsClient dfsClient = new DfsClient();
	
	 TrackerClient trackerClient = new TrackerClient(config);
	 TrackerServer trackerServer = trackerClient.getConnection();
	 
	 StorageServer storageServer = null;
	 StorageClient client = new StorageClient(trackerServer.getSocket(), storageServer);
	 
	 byte[] file_buff;
	 String file_ext_name;
	 NameValuePair[] meta_list;
	 String[] results;
	 int errno;							
	 meta_list = new NameValuePair[4];
	 meta_list[0] = new NameValuePair("width", "800");
	 meta_list[1] = new NameValuePair("heigth", "600");
	 meta_list[2] = new NameValuePair("bgcolor", "#FFFFFF");
	 meta_list[3] = new NameValuePair("author", "Mike");
	 file_buff = "this is a test".getBytes(config.getCharset());
	 file_ext_name = "txt";
	 System.out.println("file length: " + file_buff.length);
	 long startTime = System.currentTimeMillis();
	 results = client.upload_file(file_buff, file_ext_name, meta_list);
	 System.out.println("upload_file time used: " + (System.currentTimeMillis() - startTime) + " ms");
	 
	 if (results == null)
	 {
		System.err.println("upload file fail, error code: " + client.getErrorCode());
		return;
	 }
	 
	 String group_name = results[0];
	 String remote_filename = results[1];
	 System.out.println("上传文件成功： group name:" +group_name+"  remote file name: "+remote_filename);
	 client.delete_file(group_name, remote_filename);
	 System.out.println("删除文件成功！ ");
 }
}
