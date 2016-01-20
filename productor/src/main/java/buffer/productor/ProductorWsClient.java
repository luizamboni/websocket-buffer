package buffer.productor;

import java.io.IOException;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

public class ProductorWsClient implements Runnable {
	
	private Integer threadId;
	private String host;
	private Integer port;
	CountDownLatch latch;

	public ProductorWsClient(Integer threadId, String host, Integer port, CountDownLatch latch){
		this.threadId =  threadId;
		this.host = host;
		this.port = port;
		this.latch =  latch;
	}
	
	private int getRandom(){
		int max = 100;
		int min = 1;
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;	
	    return randomNum;
	}
	
	public String getName(){
		return  "Produtor " +  threadId;
	}
	
	public void run(){

		try{
		    try{
                latch.await();
            } catch (InterruptedException ex){
                ex.printStackTrace();
            } 
		    
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
   
			String uri = "ws://"+ host + ":"+ String.valueOf(port) + "/";
			
			ProductorClientEndpoint socket = new ProductorClientEndpoint();
			socket.setMessage(threadId, getRandom());
			
			container.connectToServer(socket, URI.create(uri));
	   
	    } catch (DeploymentException e) {
			e.printStackTrace();
		
	    } catch (IOException e) {
			e.printStackTrace();
		}
	    
        System.out.println( getName() );
	}
	

}
