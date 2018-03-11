package cn.chenjw.codedemo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NioServer {
	// 超时时间,单位毫秒  
    private static final int TimeOut = 3000;
    public static void main(String[] args) throws IOException {
    	// 创建一个在本地端口进行监听的服务Socket信道.并设置为非阻塞方式
    	ServerSocketChannel serverChannel = ServerSocketChannel.open();
    	serverChannel.socket().bind(new InetSocketAddress(8080));
    	serverChannel.configureBlocking(false);
    	
    	 // 创建一个选择器并将serverChannel注册到它上面  
    	Selector selector = Selector.open();
    	serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    	
    	while(true){
    		//等待某个信道就绪
    		if(selector.select(TimeOut) == 0){
    			System.out.println("..");
    			continue;
    		}
    		
    		//获取就绪信道的键迭代器
    		Iterator<SelectionKey> keyItor = selector.selectedKeys().iterator();
    		
    		//使用迭代器进行遍历就绪信道
    		while(keyItor.hasNext()){
    			SelectionKey key = keyItor.next();
    			 // 这种情况是有客户端连接过来,准备一个clientChannel与之通信  
    			if(key.isAcceptable()){
    				SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
    				clientChannel.configureBlocking(false);
    				clientChannel.register(selector, SelectionKey.OP_READ,
    						ByteBuffer.allocate(1024));
    				
    			}
    			//ke看客户端有写入时
    			if(key.isReadable()){
    				SocketChannel clientChannel = (SocketChannel) key.channel();
    				
    				//得到并重置缓冲区的主要索引值  
    				ByteBuffer buffer = (ByteBuffer) key.attachment();
    				buffer.clear();
    				
    				// 读取信息获得读取的字节数  
    				long bytesRead = clientChannel.read(buffer);
    				
    				
    				if(bytesRead == -1){
    					// 没有读取到内容的情况 
    					clientChannel.close();
    				}else{
    					 // 将缓冲区准备为数据传出状态 
    					buffer.flip();
    					// 将获得字节字符串(使用Charset进行解码)  
    					String receivedString = Charset.forName("UTF-8")
    							.newDecoder().decode(buffer).toString();
    					
    					System.out.println("服务器端接收到的信息："+receivedString);
    					String sendString = "你好,客户端. 已经收到你的信息==>:"+receivedString;
    					
    					// 将要发送的字符串编码(使用Charset进行编码)后再进行包装  
    					buffer = ByteBuffer.wrap(sendString.getBytes("UTF-8"));
    					
    					clientChannel.write(buffer);
    					// 设置为下一次读取或是写入做准备
    					key.interestOps(SelectionKey.OP_READ);
    				}
    			}
    			keyItor.remove();
    		}
    	}
	}
}
