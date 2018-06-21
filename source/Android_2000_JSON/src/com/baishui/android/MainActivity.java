package com.baishui.android;

import java.util.List;

import com.baishui.android.model.User;
import com.baishui.android.util.JsonReaderUtil;
import com.baishui.util.HttpDownLoader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
 

public class MainActivity extends Activity {
	
	private Button  jsonReaderOneBT;
	private Button  jsonReaderArrayBT;
	
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        this.jsonReaderOneBT = (Button) this.findViewById(R.id.jsonReaderOneBT); 
        this.jsonReaderArrayBT = (Button)this.findViewById(R.id.jsonReaderArrayBT);
        
        this.jsonReaderOneBT.setOnClickListener(new OnReaderOneListener()); 
        this.jsonReaderArrayBT.setOnClickListener(new OnReaderArrayListener());
     
    }
    
     class OnReaderOneListener  implements View.OnClickListener{
    	
    	@Override
    	public void onClick(View v) {
    		System.out.println("[MainActivity]:OnReaderOneListener");
    		HttpDownLoader downLoader = new HttpDownLoader();
    	    String jsonDataOne = downLoader.download("http://192.168.1.81:8080/AndroidDownload/jsonGson?requestData=one"); 
    	    
    	    System.out.println("[MainActivity]:jsonDataList : " +jsonDataOne);
    		
    	    JsonReaderUtil jsonReader = new JsonReaderUtil();   
    		User user  = jsonReader.parseJsonToUser(jsonDataOne);
    		System.out.println("username:"+user.getUsername());
			System.out.println("age:"+user.getAge());
			System.out.println("sex:"+user.getSex());
			System.out.println("------------------------");
    	}
    	
    }
    
    class OnReaderArrayListener  implements View.OnClickListener{
    	
    	@Override
    	public void onClick(View v) {
    		System.out.println("[MainActivity]:OnReaderArrayListener");
    		HttpDownLoader downLoader = new HttpDownLoader();
    	    String jsonDataList = downLoader.download("http://192.168.1.81:8080/AndroidDownload/jsonGson?requestData=list"); 
    		System.out.println("[MainActivity]:jsonDataList : " +jsonDataList);
    		
    		
    		JsonReaderUtil jsonReader = new JsonReaderUtil();  
    		List<User>  users = jsonReader.parseJsonToUsers(jsonDataList); 
    		for (User user : users) {
				System.out.println("username:"+user.getUsername());
				System.out.println("age:"+user.getAge());
				System.out.println("sex:"+user.getSex());
				System.out.println("------------------------");
			}
    		 
    	}
    	
    }
    
}