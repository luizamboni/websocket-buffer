package buffer.consumer;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;

public class ConsumerWsClient implements Runnable {
	
	private Integer threadId;
	private String host;
	private Integer port;
	CyclicBarrier barrier;
	CountDownLatch mainLatch;

	public ConsumerWsClient(Integer threadId, String host, Integer port, CyclicBarrier barrier,CountDownLatch mainLatch){
		this.threadId =  threadId;
		this.host = host;
		this.port = port;
		this.barrier =  barrier;
		this.mainLatch = mainLatch;
	}
	
	public String getName(){
		return "Consumidor " +  threadId;
		
	}
	
	public void run(){
		try{
			
		    try{
		    	barrier.await();
            } catch (BrokenBarrierException ex){
                ex.printStackTrace();
            } catch (InterruptedException ex){
            	ex.printStackTrace();
            }
		    
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
   
			String uri = "ws://"+ host + ":"+ String.valueOf(port) + "/";
			
			ConsumerClientEndpoint socket = new ConsumerClientEndpoint(threadId, mainLatch);
			
			container.connectToServer(socket, URI.create(uri));
	   
	    } catch (DeploymentException e) {
			e.printStackTrace();
		
	    } catch (IOException e) {
			e.printStackTrace();
		}
		
        System.out.println( getName() );
	}
}
	