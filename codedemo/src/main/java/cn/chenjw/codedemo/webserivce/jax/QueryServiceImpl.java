package cn.chenjw.codedemo.webserivce.jax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QueryServiceImpl implements QueryService {

	@Override
	public String queryGet(String name) {
		// TODO Auto-generated method stub
		String str = "queryGet查询的参数是：name="+name;
		return str;
	}

	@Override
	public String queryGet2(String name) {
		String str = "queryGet2查询的参数是：name="+name;
		return str;
	}

	@Override
	public String queryPost(String name) {
		JSONObject jo = JSON.parseObject(name);
		JSONObject result = new JSONObject();
		JSONArray ja = new JSONArray();
		if(jo.containsKey("id")){
			jo.put("time", System.currentTimeMillis());
			ja.add(jo);
			result.put("data", ja);
		}else{
			result.put("data", ja);
		}
		String str = result.toJSONString();
		return str;
	}

	@Override
	public String getFIleInfo(Attachment targetFile, String param) {
		try {
			DataHandler handler = targetFile.getDataHandler();
			String originalFileName = new String(handler.getName().getBytes("ISO8859-1"),"UTF-8");
			InputStream is = handler.getInputStream();  
			FileOutputStream fos = new FileOutputStream(new File("E:\\A\\up\\"+originalFileName));
			IOUtils.copy(is, fos);
			fos.close();
			is.close();
			return "filename:"+originalFileName+",param:"+param;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error";
	}

}
