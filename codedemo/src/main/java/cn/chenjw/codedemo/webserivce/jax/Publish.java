package cn.chenjw.codedemo.webserivce.jax;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class Publish {

	public static void main(String[] args) {
		pub1();
	}

	private static void pub1() {
		JAXRSServerFactoryBean jax = new JAXRSServerFactoryBean();
		jax.setResourceClasses(QueryServiceImpl.class);
		jax.setResourceProvider(QueryServiceImpl.class, new SingletonResourceProvider(new QueryServiceImpl()));
		jax.setAddress("http://127.0.0.1:9999/querybook");
		jax.create();
		System.out.println("service start");
	}

}
