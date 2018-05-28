package cn.chenjw.codedemo.webserivce.ws;

import javax.jws.WebService;


public class QueryBookServiceImpl implements QueryBookService {

	@Override
	public String getInfo(String param) {
		return "参数是："+param;
	}

}
