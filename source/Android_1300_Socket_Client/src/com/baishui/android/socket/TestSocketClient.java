package com.baishui.android.socket;

public class TestSocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		SocketClient client = new SocketClient(); 
		 
		  client.createConnect();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				 
				e.printStackTrace();
			}
			
			client.sendMessageToServer("client request msg");
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			client.CloseConnect();
			
		 
		

	}

}
