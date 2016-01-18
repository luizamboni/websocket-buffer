package buffer.productor;

import java.io.IOException;
import java.util.Random;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@ClientEndpoint
public class ProductorClientEndpoint {

	private Integer threadId;
	private Integer value;
	private long startTime;
	private long endTime;

	
	public void setMessage(Integer threadId, Integer value){
		
		this.value = value;
		this.threadId = threadId;
	}

	
	@OnOpen
	public void sendMessage(Session session) {
		
		try {
			startTime = System.currentTimeMillis();



			session.getBasicRemote().sendText("insert:Product:" + String.valueOf(threadId) + ":" + String.valueOf(value));

	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@OnMessage
	public void getMessage(String text){
		endTime = System.currentTimeMillis();
		long difference = endTime - startTime;
		System.out.println(text + " in " + String.valueOf(difference /1000.00) + " seconds" );

		
	}
	 
}