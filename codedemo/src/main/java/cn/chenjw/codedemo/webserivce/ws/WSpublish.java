package cn.chenjw.codedemo.webserivce.ws;

import javax.xml.ws.Endpoint;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

public class WSpublish {

	public static void main(String[] args) {
		//pub1();
		pub2();
	}
	/**
	 * 通过cxf提供的JaxWsServerFactoryBean 来发布
	 */
	private static void pub2() {
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		factory.setServiceClass(QueryBookServiceImpl.class);
		factory.setAddress("http://127.0.0.1:8089/query/book");
		Server server = factory.create();
		server.start();
		System.out.println("service start ");
	}
	/**
	 * 通过JAX-WS提供的Endpoint来发布webservice
	 */
	private static void pub1() {
		QueryBookServiceImpl qbsImpl = new QueryBookServiceImpl();
		String address =  "http://127.0.0.1:8089/query/book";
		Endpoint.publish(address, qbsImpl);
		System.out.println("service start ");
	}

}
