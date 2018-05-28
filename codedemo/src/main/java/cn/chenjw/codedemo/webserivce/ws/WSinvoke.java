package cn.chenjw.codedemo.webserivce.ws;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingOperationInfo;

public class WSinvoke {

	public static void main(String[] args) {
		//invoke1();
		invoke2();
		
	}
/*
 * 动态调用ws
 */
	private static void invoke2() {
		String method = "getInfo";
		String param = "1234567";
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://127.0.0.1:8089/query/book?wsdl");
		Endpoint endpoint = client.getEndpoint();
		BindingInfo info = endpoint.getEndpointInfo().getBinding();
		Collection<BindingOperationInfo> operations = info.getOperations();
		QName op = null;
		for(BindingOperationInfo bindingOperationInfo:operations){
			QName opQName = bindingOperationInfo.getOperationInfo().getName();
			if(opQName.getLocalPart().equals(method)){
				op = opQName;
				break;
			}
			System.out.println("name"+opQName.getLocalPart());
		}
		try {
			Object[] obj = client.invoke(method, param);
			System.out.println(obj);
			for(Object s :obj){
				System.out.println(s.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void invoke1() {
		JaxWsProxyFactoryBean fbean = new JaxWsProxyFactoryBean();
		fbean.setAddress("http://127.0.0.1:8089/query/book");
		fbean.setServiceClass(QueryBookService.class);
		QueryBookService service = fbean.create(QueryBookService.class);
		System.out.println(service.getInfo("你好哦"));
	}

}
