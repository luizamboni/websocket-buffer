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
	
	public ConsumerClientEndpoint(Integer threadId){
		this.threadId = threadId;
	}

	
	@OnOpen
	public void sendMessage(Session session) {
		
		try {
			startTime = System.currentTimeMillis();

			session.getBasicRemote().sendText("read:Consumer:" + String.valueOf(threadId));
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
