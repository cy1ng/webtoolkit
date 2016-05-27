

package com.cying.webtoolkit.fastdfs.tracker;

import java.io.*;
import java.util.*;
import com.cying.webtoolkit.fastdfs.common.ProtoCommon;
import com.cying.webtoolkit.fastdfs.common.ServerInfo;
import com.cying.webtoolkit.fastdfs.config.ClientConfig;
import com.cying.webtoolkit.fastdfs.storage.StorageClient1;
import com.cying.webtoolkit.fastdfs.storage.StorageServer;

import java.net.*;

public class TrackerClient
{
	
  protected TrackerGroup trackerGroup;
  protected byte errno;
  private ClientConfig config;
   
    public TrackerClient(ClientConfig config)
	{
		this.config = config;
		this.trackerGroup=config.getTracker_group();
	}
	public TrackerClient(TrackerGroup trackerGroup,ClientConfig config)
	{
		this.trackerGroup = trackerGroup;
		this.config = config;
	}

	
	/**
	* @return the error code of last call
	*/
	public byte getErrorCode()
	{
		return this.errno;
	}
	
	/**
	* get a connection to tracker server
	* @param
	* @return tracker server Socket object, return null if fail
	*/
	public TrackerServer getConnection() throws IOException
	{
		return this.trackerGroup.getConnection();
	}
	
	/**
	* query storage server to upload file
	* @param trackerSocket the Socket object of tracker server
	* @return storage server Socket object, return null if fail
	*/
	public StorageServer getStoreStorage(Socket trackerSocket) throws IOException
	{
		final String group_name = null;
		return this.getStoreStorage(trackerSocket, group_name);
	}
	
	//建立socket连接
	public  Socket getSocket(String ipAddr, int port) throws IOException
	{
		Socket sock = new Socket();
		sock.setSoTimeout(config.getNetwork_timeout());
		sock.connect(new InetSocketAddress(ipAddr, port), config.getNetwork_timeout());
		return sock;
	}
	/**
	* query storage server to upload file
	* @param trackerSocket the Socket object of tracker server
	* @param group_name the group name to upload file to, can be empty
	* @return storage server Socket object, return null if fail
	*/
	public StorageServer getStoreStorage(Socket trackerSocket, String group_name) throws IOException
	{
		OutputStream out = trackerSocket.getOutputStream();
		byte[] header;  //报文header
		String ip_addr; //ip地址
		int port;       //端口
		byte cmd;       //指令
		int out_len;
		boolean bNewConnection;
		byte store_path;
		TrackerServer trackerServer;
				
		trackerServer = getConnection();
		if (trackerServer == null)
		{
			return null;
		}
		trackerSocket = trackerServer.getSocket();
		bNewConnection = true;
		
		try
		{
			if (group_name == null || group_name.length() == 0)
			{
				cmd = ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITHOUT_GROUP;
				out_len = 0;
			}
			else
			{
				cmd = ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_STORE_WITH_GROUP;
				out_len = ProtoCommon.FDFS_GROUP_NAME_MAX_LEN;
			}
			header = ProtoCommon.packHeader(cmd, out_len, (byte)0);
			out.write(header);
			
            //如果group_name存在
			if (group_name != null && group_name.length() > 0)
			{
				byte[] bGroupName;
				byte[] bs;
				int group_len;
				
				bs = group_name.getBytes(ProtoCommon.DEFAULT_CHARSET);
				bGroupName = new byte[ProtoCommon.FDFS_GROUP_NAME_MAX_LEN];
				
				if (bs.length <= ProtoCommon.FDFS_GROUP_NAME_MAX_LEN)
				{
					group_len = bs.length;
				}
				else
				{
					group_len = ProtoCommon.FDFS_GROUP_NAME_MAX_LEN;
				}
				Arrays.fill(bGroupName, (byte)0);
				System.arraycopy(bs, 0, bGroupName, 0, group_len);
				out.write(bGroupName);
			}
	
			ProtoCommon.RecvPackageInfo pkgInfo = ProtoCommon.recvPackage(trackerSocket.getInputStream(), 
	                                     ProtoCommon.TRACKER_PROTO_CMD_SERVICE_RESP, 
	                                     ProtoCommon.TRACKER_QUERY_STORAGE_STORE_BODY_LEN);
			this.errno = pkgInfo.errno;
			if (pkgInfo.errno != 0)
			{
				return null;
			}
			
			ip_addr = new String(pkgInfo.body, ProtoCommon.FDFS_GROUP_NAME_MAX_LEN, ProtoCommon.FDFS_IPADDR_SIZE-1).trim();
	
			port = (int)ProtoCommon.buff2long(pkgInfo.body, ProtoCommon.FDFS_GROUP_NAME_MAX_LEN
	                        + ProtoCommon.FDFS_IPADDR_SIZE-1);
			store_path = pkgInfo.body[ProtoCommon.TRACKER_QUERY_STORAGE_STORE_BODY_LEN - 1];
			
			return new StorageServer(this.getSocket(ip_addr, port), store_path);
		}
		finally
		{
			if (bNewConnection)
			{
				ProtoCommon.closeSocket(trackerSocket);
			}
		}
	}

	/**
	* query storage server to download file
	* @param trackerSocket tracker server
	* @param group_name the group name of storage server
	* @param filename filename on storage server
	* @return storage server Socket object, return null if fail
	*/
	public StorageServer getFetchStorage(Socket trackerSocket, 
			String group_name, String filename) throws IOException
	{
		ServerInfo[] servers = this.getStorages(trackerSocket, ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ONE, 
				group_name, filename);
		if (servers == null)
		{
				return null;
		}
		else
		{
			return new StorageServer(servers[0].connect(), 0);
		}
	}

	/**
	* query storage server to update file (delete file or set meta data)
	* @param trackerSocket tracker server
	*	@param group_name the group name of storage server
	* @param filename filename on storage server
	* @return storage server Socket object, return null if fail
	*/
	public StorageServer getUpdateStorage(Socket trackerSocket, 
			String group_name, String filename) throws IOException
	{
		ServerInfo[] servers = this.getStorages(trackerSocket, ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE, 
				group_name, filename);
		if (servers == null)
		{
				return null;
		}
		else
		{
			return new StorageServer(servers[0].connect(), 0);
		} 
	}

	/**
	* get storage servers to download file
	* @param trackerSocket tracker server
	*	@param group_name the group name of storage server
	* @param filename filename on storage server
	* @return storage servers, return null if fail
	*/
	public ServerInfo[] getFetchStorages(Socket trackerSocket, 
			String group_name, String filename) throws IOException
	{
		return this.getStorages(trackerSocket, ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ALL, 
				group_name, filename);
	}
	
	/**
	* query storage server to download file
	* @param trackerSocket tracker server
	* @cmd command code, ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_FETCH_ONE or 
	                     ProtoCommon.TRACKER_PROTO_CMD_SERVICE_QUERY_UPDATE
	*	@param group_name the group name of storage server
	* @param filename filename on storage server
	* @return storage server Socket object, return null if fail
	*/
	protected ServerInfo[] getStorages(Socket trackerSocket, 
			byte cmd, String group_name, String filename) throws IOException
	{
		OutputStream out = trackerSocket.getOutputStream();
		byte[] header;
		byte[] bFileName;
		byte[] bGroupName; //有最大长度的限制，可能会被截短
		byte[] bs;  //groupName的全部字节
		int len;
		String ip;
		int port;
		
		bs = group_name.getBytes(ProtoCommon.DEFAULT_CHARSET);
		bGroupName = new byte[ProtoCommon.FDFS_GROUP_NAME_MAX_LEN];
		bFileName = filename.getBytes(ProtoCommon.DEFAULT_CHARSET);
		
		len = bs.length <= ProtoCommon.FDFS_GROUP_NAME_MAX_LEN ?bs.length :ProtoCommon.FDFS_GROUP_NAME_MAX_LEN;
		Arrays.fill(bGroupName, (byte)0);
		System.arraycopy(bs, 0, bGroupName, 0, len);
		
		header = ProtoCommon.packHeader(cmd, ProtoCommon.FDFS_GROUP_NAME_MAX_LEN + bFileName.length, (byte)0);
		byte[] wholePkg = new byte[header.length + bGroupName.length + bFileName.length];
		System.arraycopy(header, 0, wholePkg, 0, header.length);
		System.arraycopy(bGroupName, 0, wholePkg, header.length, bGroupName.length);
		System.arraycopy(bFileName, 0, wholePkg, header.length + bGroupName.length, bFileName.length);
		out.write(wholePkg);
		
		ProtoCommon.RecvPackageInfo pkgInfo = ProtoCommon.recvPackage(trackerSocket.getInputStream(), 
                                     ProtoCommon.TRACKER_PROTO_CMD_SERVICE_RESP, -1);
		this.errno = pkgInfo.errno;
		if (pkgInfo.errno != 0)
		{
			return null;
		}
		
		if (pkgInfo.body.length < ProtoCommon.TRACKER_QUERY_STORAGE_FETCH_BODY_LEN)
		{
			throw new IOException("Invalid body length: " + pkgInfo.body.length);
		}
		
		if ((pkgInfo.body.length - ProtoCommon.TRACKER_QUERY_STORAGE_FETCH_BODY_LEN) % (ProtoCommon.FDFS_IPADDR_SIZE - 1) != 0)
		{
			throw new IOException("Invalid body length: " + pkgInfo.body.length);
		}
		
		int server_count = 1 + (pkgInfo.body.length - ProtoCommon.TRACKER_QUERY_STORAGE_FETCH_BODY_LEN) / (ProtoCommon.FDFS_IPADDR_SIZE - 1);
		
		ip = new String(pkgInfo.body, ProtoCommon.FDFS_GROUP_NAME_MAX_LEN, ProtoCommon.FDFS_IPADDR_SIZE-1).trim();
		int offset = ProtoCommon.FDFS_GROUP_NAME_MAX_LEN + ProtoCommon.FDFS_IPADDR_SIZE - 1;
		
		port = (int)ProtoCommon.buff2long(pkgInfo.body, offset);
        offset += ProtoCommon.TRACKER_PROTO_PKG_LEN_SIZE;
    
        ServerInfo[] servers = new ServerInfo[server_count];
        servers[0] = new ServerInfo(ip, port);
        for (int i=1; i<server_count; i++)
       {
    	servers[i] = new ServerInfo(new String(pkgInfo.body, offset, ProtoCommon.FDFS_IPADDR_SIZE-1).trim(), port);
    	offset += ProtoCommon.FDFS_IPADDR_SIZE - 1;
       }

		return servers;
	}
	
	/**
	* query storage server to download file
	* @param trackerSocket tracker server
	*	@param the file id(including group name and filename)
	* @return storage server Socket object, return null if fail
	*/
	public StorageServer getFetchStorage1(Socket trackerSocket, String file_id) throws IOException
	{
		String[] parts = new String[2];
		this.errno = StorageClient1.split_file_id(file_id, parts);
		if (this.errno != 0)
		{
			return null;
		}
		
		return this.getFetchStorage(trackerSocket, parts[0], parts[1]);
	}
	
	/**
	* get storage servers to download file
	* @param trackerSocket tracker server
	*	@param the file id(including group name and filename)
	* @return storage servers, return null if fail
	*/
	public ServerInfo[] getFetchStorages1(Socket trackerSocket, String file_id) throws IOException
	{
		String[] parts = new String[2];
		this.errno = StorageClient1.split_file_id(file_id, parts);
		if (this.errno != 0)
		{
			return null;
		}
		
		return this.getFetchStorages(trackerSocket, parts[0], parts[1]);
	}


	public ClientConfig getConfig() {
		return config;
	}


	public void setConfig(ClientConfig config) {
		this.config = config;
	}
}
