package com.cying.webtoolkit.fastdfs.config;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import com.cying.webtoolkit.fastdfs.tracker.TrackerGroup;

public class ClientConfig implements Cloneable {
  
	private  int     network_timeout; //unit is millisecond
	private  String  charset;
	private  int     tracker_http_port;
	private  boolean anti_steal_token;  //if anti-steal token
	private  String  secret_key;   //generage token secret key
	private  TrackerGroup tracker_group;
	
	ClientConfig(final int network_timeout,
			     final String charset,
			     final int tracker_http_port,
			     final boolean anti_steal_token,
			     final String secret_key,
			     final TrackerGroup tracker_group
			     ){
		super();
		this.network_timeout=network_timeout;
		this.charset=charset;
		this.tracker_http_port=tracker_http_port;
		this.anti_steal_token=anti_steal_token;
		this.secret_key=secret_key;
		this.tracker_group=tracker_group;
	}
	
	
	
/**
* construct Socket object
* @param ip_addr ip address or hostname
* @param port port number
* @return connected Socket object
*/
	public  Socket getSocket(String ip_addr, int port) throws IOException
	{
		Socket sock = new Socket();
		sock.setSoTimeout(network_timeout);
		sock.connect(new InetSocketAddress(ip_addr, port), network_timeout);
		return sock;
	}
	
	
	
	
    public int getNetwork_timeout() {
		return network_timeout;
	}
	public String getCharset() {
		return charset;
	}
	public int getTracker_http_port() {
		return tracker_http_port;
	}
	public boolean isAnti_steal_token() {
		return anti_steal_token;
	}
	public String getSecret_key() {
		return secret_key;
	}
	public TrackerGroup getTracker_group() {
		return tracker_group;
	}
	
	public static Builder custom(){
    	return new Builder();
    }
	
    
   @Override
   public ClientConfig clone() throws CloneNotSupportedException{
	   return(ClientConfig)super.clone();
   }
    
    
	@Override
    public String toString() {
	return "ClientConfig [network_timeout=" + network_timeout + ", charset=" + charset + ", tracker_http_port="
			+ tracker_http_port + ", anti_steal_token=" + anti_steal_token + ", secret_key=" + secret_key
			+ ", tracker_group=" + tracker_group + "]";
}

   public static Builder copy(final ClientConfig config){
	    return new Builder().setAntiStealToken(config.isAnti_steal_token())
	    		            .setCharset(config.getCharset())
	    		            .setNetworkTimeout(config.getNetwork_timeout())
	    		            .setSecretKey(config.getSecret_key())
	    		            .setTrackerGroup(config.getTracker_group())
	    		            .setTrackerHttpPort(config.getTracker_http_port());
   }
   
   
   
   
	public static class Builder {
		  
		private  int     network_timeout; //unit is millisecond
		private  String  charset;
		private  int     tracker_http_port;
		private  boolean anti_steal_token;  //if anti-steal token
		private  String  secret_key;   //generage token secret key
		private  TrackerGroup tracker_group;
		
		public ClientConfig build(){
			return new ClientConfig(network_timeout, charset, tracker_http_port, anti_steal_token, secret_key, tracker_group);
		}
		
		public Builder setNetworkTimeout(final int network_timeout){
			this.network_timeout = network_timeout;
			return this;
		}
		
		public Builder setCharset(final String charset){
			this.charset = charset;
			return this;
		}
		
		public Builder setTrackerHttpPort(int tracker_http_port){
			this.tracker_http_port = tracker_http_port;
			return this;
		}
		
		public Builder setAntiStealToken(final boolean anti_steal_token){
			this.anti_steal_token = anti_steal_token;
			return this;
		}
		
		public Builder setSecretKey(final String secret_key){
			this.secret_key = secret_key;
			return this;
		}
		
		public Builder setTrackerGroup(final TrackerGroup tracker_group){
			this.tracker_group = tracker_group;
			return this;
		}
	
	}
}
