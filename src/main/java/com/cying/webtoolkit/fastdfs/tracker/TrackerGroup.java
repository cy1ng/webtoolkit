/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package com.cying.webtoolkit.fastdfs.tracker;

import java.io.*;

import java.net.*;

import com.cying.webtoolkit.fastdfs.config.ClientConfig;

public class TrackerGroup
{
	protected Integer lock;
	public int tracker_server_index;
	public InetSocketAddress[] tracker_servers;
	private ClientConfig config;
	
	/*根据多个tracker的地址，初始化TrackerGroup*/
	public TrackerGroup(InetSocketAddress[] tracker_servers)
	{
		this.tracker_servers = tracker_servers;
		this.lock = new Integer(0);
		this.tracker_server_index = 0;
	}
	
	/**
	* return connected tracker server
	* @param
	* @return connected tracker server, null for fail
	*/
	public TrackerServer getConnection() throws IOException
	{
		Socket sock;
		int current_index;
		
		synchronized(this.lock)
		{
			this.tracker_server_index++;
			if (this.tracker_server_index >= this.tracker_servers.length)
			{
				this.tracker_server_index = 0;
			}
			
			current_index = this.tracker_server_index;
		}
		
		try
		{
			sock = new Socket();
			sock.setSoTimeout(config.getNetwork_timeout());
			sock.connect(this.tracker_servers[current_index], config.getNetwork_timeout());
			return new TrackerServer(sock, this.tracker_servers[current_index]);
	  }
	  catch(IOException ex)
	  {
	  	ex.printStackTrace(System.err);
	  }
	  
	  for (int i=0; i<this.tracker_servers.length; i++)
	  {
	  	if (i == current_index)
	  	{
	  		continue;
	  	}
	  	
			try
			{
				sock = new Socket();
				sock.setSoTimeout(config.getNetwork_timeout());
				sock.connect(this.tracker_servers[i], config.getNetwork_timeout());
				return new TrackerServer(sock, this.tracker_servers[i]);
		  }
		  catch(IOException ex)
		  {
		  	ex.printStackTrace(System.err);
		  }
	  }
	  
	  return null;
	}

	public ClientConfig getConfig() {
		return config;
	}

	public void setConfig(ClientConfig config) {
		this.config = config;
	}
}
