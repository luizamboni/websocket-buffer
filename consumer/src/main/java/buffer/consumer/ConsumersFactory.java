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
		
	}
	
	public void run(){
		for (int i = 0; i < quantity; i++){
			ConsumerWsClient thread = new ConsumerWsClient(i+1, host, port);
			this.threads.add(new Thread(thread)) ;
		}
		
		for(Thread thread : threads){
			thread.start();
		}
		
	}
}
