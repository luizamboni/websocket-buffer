package buffer.consumer;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ConsumersFactory {

	
	private ArrayList<Thread> threads;
	private String host;
	private Integer port;
	private Integer quantity;
	CountDownLatch latch;
	CountDownLatch mainLatch;
	
	public ConsumersFactory(Integer quantity, String host, Integer port){
		
		this.threads = new ArrayList<Thread>();
		this.host = host;
		this.port = port;
		this.quantity = quantity;
		latch = new CountDownLatch(1);
		mainLatch = new CountDownLatch(quantity);
		
		for (int i = 0; i < quantity; i++){
			ConsumerWsClient runnable = new ConsumerWsClient(i+1, host, port,latch, mainLatch);
			this.threads.add(new Thread(runnable)) ;
		}
	}
	
	public void run(){

		
		for(Thread thread : threads){ thread.start(); }
		
		new Thread()
		{
		    public void run() {
				latch.countDown();
		    }
		}.start();
		
		
		try {
			mainLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    System.out.println("All threads have finished execution");

		
	}
}
