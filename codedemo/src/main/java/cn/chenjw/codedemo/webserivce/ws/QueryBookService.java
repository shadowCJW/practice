package cn.chenjw.codedemo.webserivce.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface QueryBookService {
	@WebMethod
	String getInfo(@WebParam(name="param") String param);
	
}
