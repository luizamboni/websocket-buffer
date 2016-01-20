package buffer.consumer;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class ConsumerWsClient implements Runnable {
	
	private Integer threadId;
	private String host;
	private Integer port;
	
	public ConsumerWsClient(Integer threadId, String host, Integer port){
		this.threadId =  threadId;
		this.host = host;
		this.port = port;
	}
	
	public String getName(){
		return "Consumidor " +  threadId;
		
	}
	
	public void run(){
		try{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
   
			String uri = "ws://"+ host + ":"+ String.valueOf(port) + "/";
			
			ConsumerClientEndpoint socket = new ConsumerClientEndpoint(threadId);
			
			container.connectToServer(socket, URI.create(uri));
	   
	    } catch (DeploymentException e) {
			e.printStackTrace();
		
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
        System.out.println( getName() );
	}
}
	