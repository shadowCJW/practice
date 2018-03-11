package  cn.chenjw.codedemo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class Demo_inetaddress {
	public static void main(String[] args) {
		//inetaddr();
		urldemo();
	}

	private static void inetaddr() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String hostName = addr.getHostName();
			String hostAddress = addr.getHostAddress();
			byte[] bytes = addr.getAddress();//获取字节数组形式的IP地址,以点分隔的四部分
			System.out.println(hostName);
			System.out.println(hostAddress);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	private static void urldemo() {
		 //使用URL读取网页内容
		 //创建一个URL实例
		 URL url;
		try {
			url = new URL("http://www.baidu.com");
			InputStream is = url.openStream();//通过openStream方法获取资源的字节输入流
			InputStreamReader isr =new InputStreamReader(is,"UTF-8");//将字节输入流转换为字符输入流,如果不指定编码，中文可能会出现乱码
			BufferedReader br =new BufferedReader(isr);//为字符输入流添加缓冲，提高读取效率
			String data = br.readLine();//读取数据
			while(data!=null){
				System.out.println(data);//输出数据
			    data = br.readLine();
			}
			br.close();
			isr.close();
			is.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
