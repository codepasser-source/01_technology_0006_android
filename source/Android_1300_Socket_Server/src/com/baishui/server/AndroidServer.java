package com.baishui.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class AndroidServer {
	
	public static void main(String []args){ 
		AndroidServer mainServer = new AndroidServer();
		mainServer.start();
	} 
	
	private static final int SERVER_PORT = 1314;
	private ServerSocket server = null;
	private boolean started = false;
	 
	
	//启动socket 服务方法
	private void start(){
		
		try {
			//创建Socket服务 端口 1314
			server = new ServerSocket(SERVER_PORT);
			System.out.println("[SocketServer]: Socket Server started  [ip:127.0.0.1] [port:"+SERVER_PORT+"]");
			started = true;
		} catch (BindException e) { 
			 System.out.println("[SocketServer]: BindException 端口号已被占用，请先关闭进程在启动......");
			 System.exit(0);
			 e.printStackTrace();	
		} catch (IOException e) {
			System.out.println("[SocketServer]: IOException 端口号已被占用，请先关闭进程在启动......");
			System.exit(0);
			e.printStackTrace();
		}
		    //Server 监听 socket client 连接 client 存储变量
		    Socket client=null; 
		    ClientListener clientListener=null;
		       while(started){ 
			     try {  
			      //监听socket client连接
				  client = server.accept();
				  System.out.println("[SocketServer]: 监听到一个客户端已连接");
				  //得到客户端client连接,创建一个监听器，启动当前客户端监听线程，监听客户端输入流
				   clientListener = new ClientListener(client);  
                   new Thread(clientListener).start();  
			     } catch (IOException e) { 
				   e.printStackTrace();
			     }   
		      }   
	}
 
	//客户端 数据监听线程类
	private class ClientListener implements Runnable{

		Socket client;
		DataInputStream dataInputStream; 
		DataOutputStream dataOutputStream;
		boolean connected = false;
		
		public ClientListener(Socket client) {
			 this.client=client;
			 if(client!=null){
				 try {
					 //创建当前连接 输入流
					 this.dataInputStream = new DataInputStream(this.client.getInputStream());
					 //创建当前连接 输出流
					 this.dataOutputStream = new DataOutputStream(client.getOutputStream());
					 //是否连接判断变量
					 connected = true;
					 System.out.println("[SocketServer]: 一个client监听线程已启动");
				 } catch (IOException e) { 
					e.printStackTrace();
				 } 
			 }  
		}  
		
		@Override
		public void run(){ 
			try {
			  while(connected){ //无异常情况 启动监听
					//接受客户端数据
				    String requestMsg = this.dataInputStream.readUTF(); 
				    
					//向客户端发送应答
					dataOutputStream.writeUTF(requestMsg);
					System.out.println("[SocketServer]: SocketServer Listener request msg:"+requestMsg); 
			  }  
			}catch(EOFException e){
				 System.out.println("[SocketServer]: socket client close");
			 }catch (IOException e) { 
				e.printStackTrace();
			}finally{
				try {
				  if(dataInputStream!=null){ 
					  dataInputStream.close(); 
					  dataInputStream = null;
				  }
				  if(dataOutputStream!=null){
					  dataOutputStream.close();
					  dataOutputStream=null;
				  }
				  if(client!=null){ 
					  client.close(); 
					  client = null;
				  }
				   
				  try {
					//释放当前线程
					this.finalize(); 
				   } catch (Throwable e) { 
					  e.printStackTrace();
				   } 
				} catch (IOException e) { 
					e.printStackTrace();
			    } 
			}
		} 
	}//end thread class
	
	
	 
}
