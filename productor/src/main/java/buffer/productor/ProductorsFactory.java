package buffer.productor;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ProductorsFactory {
	
	private ArrayList<Thread> threads;
	private String host;
	private Integer port;
	private Integer quantity;
	CountDownLatch latch;
	CountDownLatch mainLatch;

	public ProductorsFactory(Integer quantity, String host, Integer port){
		
		this.threads = new ArrayList<Thread>();
		this.host = host;

		this.port = port;
		this.quantity = quantity;
		latch = new CountDownLatch(quantity);
		mainLatch = new CountDownLatch(quantity);

		for (int i = 0; i < quantity; i++){
			ProductorWsClient runnable = new ProductorWsClient(i+1, host, port, latch, mainLatch);
			this.threads.add(new Thread(runnable)) ;
		}
	}
	
	public void run(){
		

		
		for(Thread thread : threads){ thread.start(); }
		
		new Thread()
		{
		    public void run() {
				for(Thread thread : threads){
					latch.countDown();				    
				}
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
