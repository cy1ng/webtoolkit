package com.cying.webtoolkit.service.maven.search;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class MavenJarSearchClient {
     
	public String search(String url){

		HttpClientBuilder clientBuilder  = HttpClients.custom();
		CloseableHttpClient client = clientBuilder.build();
		HttpPost post = new HttpPost(url);
		RequestConfig  config = RequestConfig.custom().build();
		post.setConfig(config);
		CloseableHttpResponse resp =null;
		try {
			resp = client.execute(post);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			if(resp != null){
			  return EntityUtils.toString(resp.getEntity());
			}else{
				return null;
			}    
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
