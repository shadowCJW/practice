package cn.chenjw.codedemo.webserivce.resttemplate;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

public class RESTDemo {
	public static void main(String[] args) {
		String url = "http://127.0.0.1:9999/querybook";
		
		RestTemplate client = new RestTemplate();
		// 1-getForObject() 
		String res = client.getForObject(url+"/queryget/{name}", String.class, "xiaoming");
		System.out.println(res);
		System.out.println("====");
		//2-getForEntity() 
		Map<String, Object > param = new HashMap<String, Object>();
		param.put("name", "xiaohong");
		ResponseEntity<String> res2 = client.getForEntity(url+"/queryget/{name}", String.class, param);
		System.out.println(res2.getStatusCode());
		System.out.println(res2.getHeaders());
		System.out.println(res2.getBody());
		
		System.out.println("====");
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Object> ent = new HttpEntity<Object>(headers);
		ResponseEntity<String> res3 = client.exchange(url+"/queryget/{name}", HttpMethod.GET, ent, String.class, "名字");
		
		System.out.println(res3.getBody());
		
		
		System.out.println("===post====");
		JSONObject jo = new JSONObject();
		jo.put("id", "ggg");
		String res4 = client.postForObject(url+"/querypost", jo, String.class);
		System.out.println(res4);
		
		System.out.println("===");
		
/*		 HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
	        factory.setReadTimeout(readTimeout);// 超时设置
	        factory.setConnectTimeout(connectTimeout);
	        RestTemplate client = new RestTemplate(factory);
//	        client.setMessageConverters(Lists.newArrayList(new StringHttpMessageConverter(Charset.forName(charset))));//字符集  这里不能这样设置，这样会覆盖掉整个list的转换器，这里有很多个转换器
	        List<HttpMessageConverter<?>> httpMessageConverterList = client.getMessageConverters();
	        for(int i =0 ;i<httpMessageConverterList.size();i++){
	            HttpMessageConverter httpMessageConverter = httpMessageConverterList.get(i);
	            if(httpMessageConverter instanceof StringHttpMessageConverter){
	                client.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName(charset)));//替换字符串编码转换器
	            }
	        }*/
	}
}
