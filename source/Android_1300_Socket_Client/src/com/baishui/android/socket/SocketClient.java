package com.baishui.android.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.baishui.android.MainActivity;

 

public class SocketClient {

	   private static final String SERVER_IP = "192.168.1.81";
	   private static final int SERVER_PORT = 1314;
	   private Socket client = null; 
	   private boolean connected = false;

	   private DataInputStream dataInputStream=null;
	   private DataOutputStream dataOutputStream=null;
	
	   
	   /**
	    * 创建连接
	 * @throws MySocketClientException 
	    * @throws Exception 
	    */ 
	   public void createConnect(){
	    	 try {
	    		 
			    client = new Socket(SERVER_IP,SERVER_PORT); 
			    System.out.println("[SocketClient]: 服务器已连接"); 
			   
			    connected=true;
			    dataInputStream = new DataInputStream(client.getInputStream());
			    dataOutputStream = new DataOutputStream(client.getOutputStream());
				
				ServerListener serverListener = new ServerListener(); 
				new Thread(serverListener).start();
				
			} catch (UnknownHostException e) {
				System.out.println("[SocketClient]: UnknownHostException 无法连接服务器地址和指定端口");
				e.printStackTrace(); 
			} catch (ConnectException e) {
				System.out.println("[SocketClient]: ConnectException 服务器连接失败");
				e.printStackTrace();
			 
			} catch (IOException e) {
				System.out.println("[SocketClient]: IOException socket ioException");
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}
	    } 
	  
	   /**
	    * 关闭连接
	    */
	   public void CloseConnect(){
	    	try {
	    		if(dataInputStream!=null){
	    			dataInputStream.close();
	    			dataInputStream=null;
	    			System.out.println("[SocketClient]: socket client exit: dataInputStream.close(); ");
	    		}
	    		if(dataOutputStream!=null){
	    			dataOutputStream.close();
	    			dataOutputStream=null;
	    			System.out.println("[SocketClient]: socket client exit: dataOutputStream.close(); ");
	    		}
	    		if(client!=null){
	    			client.close();
	    			client=null;
	    			System.out.println("[SocketClient]: socket client exit: client.close(); ");
	    		} 
	    		  
			} catch (IOException e) { 
				e.printStackTrace();
			}    	
	    }
	    
	   /**
	    * 发送数据
	    * @param requestMsg
	    */
	   public void sendMessageToServer(String requestMsg){
		   if(requestMsg!=""){
			   try {
				   System.out.println("[SocketClient]: sendMessageToServer requestMsg : "+requestMsg);
				   dataOutputStream.writeUTF(requestMsg);
				   dataOutputStream.flush();
				   
			    }catch(SocketException e1){
			    	System.out.println("[SocketClient]: SocketException 无法连接服务器,请重启再次尝试连接服务器"); 
			    	e1.printStackTrace();
			    }catch (IOException e1) {
			    	System.out.println("[SocketClient]: IOException 无法连接服务器,请重启再次尝试连接服务器");
				    e1.printStackTrace();
			    }catch (NullPointerException e2) {
			    	System.out.println("[SocketClient]: NullPointerException 当前服务器无连接");  
					e2.printStackTrace();
				}
			    
			}  
	   }
	   
	    
	    private class ServerListener implements  Runnable{  
	   	 
			
			@Override
			public void run() {
				 try {
				    while(connected){ 
						String responseMsg = dataInputStream.readUTF(); //
						//chatContent.setText(chatContent.getText()+msg+"\n");
						System.out.println("[SocketClient]: SocketClient Listener response responseMsg:"+responseMsg);
						MainActivity.SERVER_RESPONSE=responseMsg;
						//得到响应数据之后 关闭当前socket连接
						CloseConnect();
				    }
				 }catch(EOFException e) { 
					    //服务器 端关闭流时出发 ，服务不停止的情况下不会发生
					    System.out.println("[SocketClient]:服务器断开连接");
						e.printStackTrace();
				 }catch(IOException e) { 
					    //客户端关闭输入流时触发，（或者服务端关闭输出流）
					    System.out.println("[SocketClient]: socket client closed"); 
					   // e.printStackTrace();
				 }catch (NullPointerException e) {
					 //执行CloseConnect();触发
					 System.out.println("[SocketClient]: socket client exit"); 
				 }finally{
					 try {
						  if(dataInputStream!=null){ 
							  dataInputStream.close(); 
							  dataInputStream = null;
							  System.out.println("[SocketClient]: dataInputStream.close(); ");
						  }
						  if(dataOutputStream!=null){
							  dataOutputStream.close();
							  dataOutputStream=null;
							  System.out.println("[SocketClient]: dataOutputStream.close(); ");
						  }
						  if(client!=null){ 
							  client.close(); 
							  client = null;
							  System.out.println("[SocketClient]: client.close(); ");
						  }
						   try {
							this.finalize(); 
						   } catch (Throwable e) { 
							e.printStackTrace();
						   } 
						} catch (IOException e) { 
							e.printStackTrace();
					    } 
					 
				 } 
			} 
		}
}
