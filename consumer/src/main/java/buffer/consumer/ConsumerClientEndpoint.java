package buffer.consumer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class ConsumerClientEndpoint {
	
	private Integer threadId;
	private long startTime;
	private long endTime;
	private String response;
	private CountDownLatch mainLatch;
	
	public String getResponse(){
		return response;
	}
	
	
	public ConsumerClientEndpoint(Integer threadId, CountDownLatch mainLatch){
		this.threadId = threadId;
		this.mainLatch = mainLatch;
	}

	
	public String sentMessage(){
		return "Consumer:" + String.valueOf(threadId) + ":read" ;
	}
	
	@OnClose
	public void onClose(){
		mainLatch.countDown();
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
		response = null;
		endTime = System.currentTimeMillis();
		long difference = endTime - startTime;
		response =  text + " in " + String.valueOf(difference /1000.00) + " seconds";
		System.out.println(response );

		
	}
	 
}
