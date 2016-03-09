package buffer.consumer;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ConsumersFactory {

	
	private ArrayList<Thread> threads;
	private String host;
	private Integer port;
	private Integer quantity;
	CyclicBarrier barrier;
	CountDownLatch mainLatch;
	
	public ConsumersFactory(Integer quantity, String host, Integer port){
		
		this.threads = new ArrayList<Thread>();
		this.host = host;
		this.port = port;
		this.quantity = quantity;
		barrier = new CyclicBarrier(quantity);
		mainLatch = new CountDownLatch(quantity);
		
		for (int i = 0; i < quantity; i++){
			ConsumerWsClient runnable = new ConsumerWsClient(i+1, host, port,barrier, mainLatch);
			this.threads.add(new Thread(runnable)) ;
		}
	}
	
	public void run(){

		
		for(Thread thread : threads){ thread.start(); }
		
		try {
			mainLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	    System.out.println("All threads have finished execution");

		
	}
}
