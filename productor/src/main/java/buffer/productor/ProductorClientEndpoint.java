package buffer.productor;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;


@ClientEndpoint
public class ProductorClientEndpoint {

	private Integer threadId;
	private Integer value;
	private long startTime;
	private long endTime;
    private String response;
	CountDownLatch mainLatch;

    public String getResponse(){
    	return response;
    }
    
    

    public ProductorClientEndpoint(CountDownLatch mainLatch){
    	this.mainLatch = mainLatch;

    }

    
	public void setMessage(Integer threadId, Integer value){
		
		this.value = value;
		this.threadId = threadId;
	}

	public String sentMessage(){
		return "Productor:" + String.valueOf(threadId) + ":insert:" + String.valueOf(value);
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
	
	@OnClose
	public void onClose(){
		mainLatch.countDown();
//		System.out.println("countdown " +mainLatch.getCount());

	}
	
	@OnMessage
	public void getMessage(String text){
		response = null;
		endTime = System.currentTimeMillis();
		long difference = endTime - startTime;
		response = text + " in " + String.valueOf(difference /1000.00) + " seconds";
		System.out.println(response);

		
	}
	 
}
