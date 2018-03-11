package cn.chenjw.codedemo.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientTest {

	public static void main(String[] args) {

		/**
		 * 一下是简单的httpget请求的简单流程，创建客户端，得到get请求对象，访问请求，得到结果
		 */
		CloseableHttpClient chClient = HttpClients.createDefault(); 
		HttpGet hget = new HttpGet("http路径");
		try {
			HttpResponse resp =  chClient.execute(hget);
			int code = resp.getStatusLine().getStatusCode();
			HttpEntity entity = resp.getEntity();
			String result = EntityUtils.toString(entity);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				chClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
