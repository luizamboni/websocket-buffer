package buffer.consumer;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ConsumerClientEndpoint {
	
	private Integer threadId;
	private long startTime;
	private long endTime;
	private String response;
	
	public String getResponse(){
		return response;
	}
	
	
	public ConsumerClientEndpoint(Integer threadId){
		this.threadId = threadId;
	}

	
	public String sentMessage(){
		return "read:Consumer:" + String.valueOf(threadId);
	}
	
	@OnOpen
	public void sendMessage(Session session) {
		
		try {
			startTime = System.currentTimeMillis();

			session.getBasicRemote().sendText( sentMessage() );
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	@OnMessage
	public void getMessage(String text){
		endTime = System.currentTimeMillis();
		long difference = endTime - startTime;
		response =  text + " in " + String.valueOf(difference /1000.00) + " seconds";
		System.out.println(response );

		
	}
	 
}
