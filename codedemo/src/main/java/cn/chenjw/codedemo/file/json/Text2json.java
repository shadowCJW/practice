package cn.chenjw.codedemo.file.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Text2json {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("D:/A/abc/12356.log");
		List<ShareInfo> infoList = new ArrayList<ShareInfo>();
		try {
			String filename = file.getName();
			String seq = filename.substring(0, filename.indexOf("."));
			System.out.println(seq);
			String jsonStr  = IOUtils.toString(new FileInputStream(file), "UTF-8");
			JSONObject jo = JSONObject.parseObject(jsonStr);
			Set<String> keySet = jo.keySet();
			
			for(String s : keySet){
				JSONArray ja = (JSONArray) jo.get(s);
				setInfo(ja,s,seq,infoList);
			}
			show(infoList);
			System.out.println(jo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void show(List<ShareInfo> infoList) {
		// TODO Auto-generated method stub
		for(ShareInfo s : infoList){
			System.out.println(s.toString());
		}
	}

	private static void setInfo(JSONArray ja,String param,String seq, List<ShareInfo> infoList) {
		for(int i=0,len=ja.size();i<len;i++){
			ShareInfo si = new ShareInfo();
			si.setSeq(seq);
			si.setKey(param);
			si.setVal(ja.get(i).toString());
			infoList.add(si);
		}
	}

}
