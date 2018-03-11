package  cn.chenjw.codedemo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Demo_sock_client {
	public static void main(String[] args) {
		publishClient();
	}

	private static void publishClient() {
		// TODO Auto-generated method stub
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 8089);
			OutputStream os  = clientSocket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("我是XXX用户。。。");
			pw.flush();
			clientSocket.shutdownOutput();
			//获取输入流
			InputStream is = clientSocket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String info = null;
			while((info=br.readLine()) != null){
				System.out.println("我是客户端，服务器说："+info);
			}
			//关闭资源
			br.close();
			is.close();
			pw.close();
			os.close();
			clientSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
