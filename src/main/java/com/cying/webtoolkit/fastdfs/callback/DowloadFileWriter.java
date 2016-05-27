/**
* Copyright (C) 2008 Happy Fish / YuQing
*
* FastDFS Java Client may be copied only under the terms of the GNU Lesser
* General Public License (LGPL).
* Please visit the FastDFS Home Page http://www.csource.org/ for more detail.
*/

package com.cying.webtoolkit.fastdfs.callback;

import java.io.*;

/**
* DowloadCallback test
* @author Happy Fish / YuQing
* @version Version 1.3
*/
public class DowloadFileWriter implements DowloadCallback
{
	private String filename;
	private FileOutputStream out = null;
	private long current_bytes = 0;
	
	public DowloadFileWriter(String filename)
	{
		this.filename = filename;
	}
	
	public int recv(long file_size, byte[] data, int bytes)
	{
		try
		{
			if (this.out == null)
			{
				this.out = new FileOutputStream(this.filename);
			}
		
			this.out.write(data, 0, bytes);
			this.current_bytes += bytes;
			
			if (this.current_bytes == file_size)
			{
				this.out.close();
				this.out = null;
				this.current_bytes = 0;
			}
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
			return -1;
		}
		
		return 0;
	}
	
	protected void finalize() throws Throwable
	{
		if (this.out != null)
		{
			this.out.close();
			this.out = null;
		}
	}
}

