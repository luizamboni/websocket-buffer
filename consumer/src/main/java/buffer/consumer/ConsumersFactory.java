package buffer.consumer;
import java.util.ArrayList;

public class ConsumersFactory {

	
	private ArrayList<Thread> threads;
	private String host;
	private Integer port;
	private Integer quantity;
	
	public ConsumersFactory(Integer quantity, String host, Integer port){
		
		this.threads = new ArrayList<Thread>();
		this.host = host;
		this.port = port;
		this.quantity = quantity;
		for (int i = 0; i < quantity; i++){
			ConsumerWsClient runnable = new ConsumerWsClient(i+1, host, port);
			this.threads.add(new Thread(runnable)) ;
		}
	}
	
	public void run(){

		
		for(Thread thread : threads){
			try {
				
				thread.start();
				thread.join();

			} catch (InterruptedException e) {
				System.out.println( thread.getName() + " fails");
				e.printStackTrace();
			}
		}
		
	    System.out.println("All threads have finished execution");

		
	}
}
