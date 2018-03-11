package  cn.chenjw.codedemo.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo_socket {
	public static void main(String[] args) {
		publishServer();
	}

	private static void publishServer() {
		try {
			//创建服务端
			ServerSocket serverSocket = new ServerSocket(8089);
			//socket监听客户端连接
			Socket socket = serverSocket.accept();
			//获取输入流
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String info = null;
			while((info = br.readLine()) != null){
				System.out.println("这里是服务器，客户端请求是： "+ info);
			}
			socket.shutdownInput();//关闭输入流
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("欢迎你！");
			pw.flush();
			
			//关闭资源
			pw.close();
			os.close();
			br.close();
			isr.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
