package com.cying.webtoolkit.fastdfs.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;;

public class ServerInfo
{
	protected String ip;
	protected int port;
	
	public ServerInfo(String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}

	public Socket connect() throws IOException
	{
		Socket sock = new Socket();
		sock.setSoTimeout(ProtoCommon.DEFAULT_TIMEOUT);
		sock.connect(new InetSocketAddress(this.ip, this.port), ProtoCommon.DEFAULT_TIMEOUT);
		return sock;
	}
}
