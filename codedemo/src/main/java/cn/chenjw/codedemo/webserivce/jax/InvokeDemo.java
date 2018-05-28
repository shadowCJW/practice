package cn.chenjw.codedemo.webserivce.jax;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.ClientConfiguration;
import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class InvokeDemo {
	public static void main(String[] args) {
		inv();
		//inv2();
		//inv3();
	}
	private static void inv3() {
		String result = "";
		HttpClientBuilder bulider = HttpClientBuilder.create();
		CloseableHttpClient httpclient = bulider.build();
		//HttpGet get = new HttpGet("http://127.0.0.1:8088/querybook/queryget?name=123");
		HttpGet get = new HttpGet("http://127.0.0.1:8088/querybook/queryget/22123");
		try {
			CloseableHttpResponse response = httpclient  
			        .execute(get);
			 HttpEntity httpEntity = response.getEntity();
			  result = EntityUtils.toString(httpEntity, "UTF-8");
			// System.out.println(string);
			  httpclient.close();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println(result);
		}
/**
 * httppost
 */
	private static void inv2() {
		
		HttpClientBuilder bulider = HttpClientBuilder.create();
		CloseableHttpClient httpclient = bulider.build();
		
		HttpPost httppost = new HttpPost("http://127.0.0.1:8088/querybook/querypost");
		RequestConfig config = RequestConfig.custom()  
                .setSocketTimeout(1000*60)  
                .setConnectTimeout(1000*60).build(); 
		  String result = "";
		  JSONObject jo = new JSONObject();
			jo.put("name", "hhh22");
		  httppost.setConfig(config);
		  try {
			StringEntity ent = new StringEntity(jo.toJSONString(), "UTF-8");
			httppost.setEntity(ent);
			 CloseableHttpResponse response = httpclient  
	                    .execute(httppost);  
			 HttpEntity httpEntity = response.getEntity();
			  result = EntityUtils.toString(httpEntity, "UTF-8");
			// System.out.println(string);
			  response.close();
			  httpclient.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		  System.out.println(result);
	}
	/**
	 * 采用webclient调用rs
	 */
	private static void inv() {
		 // 手动创建webClient对象，注意这里的地址是发布的那个/rest地址  
			String wsdlUrl = "http://127.0.0.1:8088/querybook/querypost";
			JSONObject jo = new JSONObject();
			jo.put("id", 123);
		     WebClient client = WebClient.create(wsdlUrl); 

		     // 设置超时  
		     ClientConfiguration config = WebClient.getConfig(client);  
		     config.getHttpConduit().getClient().setReceiveTimeout(90000);  
		     WebClient type = client.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON);
		    // Response post = type.post(jo.toJSONString());
		     String post2 = type.post(jo.toJSONString(), String.class);
		     System.out.println(post2); ;
	}
}
