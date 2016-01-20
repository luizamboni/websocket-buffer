package buffer.server;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.concurrent.Semaphore;

import javax.websocket.*;
import javax.websocket.server.*;

@ServerEndpoint(value = "/")
public class BufferEndpoint { 

	
	private Buffer buffer;
    static Semaphore mutex;
    private String response;
    
    public String getResponse(){
    	return response;
    }
    
	public BufferEndpoint(){
		this.buffer =  Buffer.getInstance();
		this.mutex =  new Semaphore(1);
	}
	
	
    @OnOpen
    public void onOpen() {

    }
    
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
    	
    	response = null;


        /* formato de mensagem insert comand:id:type:value
         * poderia sem num json também, mas no caso presente nâo vi 
         * necessidade e preferi uma sintaxa proxima as chaves do Redis
         * 
    	 *	Exemplos
    	 *  
    	 * 		Productor:5:insert:40
    	 *		Consumer:5:read
    	 */
    	
    	String command = message.split(":" )[2];
    	String type = message.split(":" )[0];	
    	String thread_id = message.split(":" )[1];	
    	Integer value;
    	
    	
    	try{
    		
        	mutex.acquire();
        	
	    	if(command.equals("insert")){ 
	    		
	        	value = Integer.valueOf( message.split(":" )[3] );	
	            
	            buffer.insert( value);
	    		response =   Colors.ANSI_GREEN + "Valor "+ value.toString() + " adicionado em Buffer pelo "+ type + " " +  thread_id + Colors.ANSI_RESET;
	    		sendMessage(session,response);
		    }else if(command.equals("read")){

		    	value  = buffer.read();
	    		response =   Colors.ANSI_GREEN + "Valor "+ value.toString() + " retirado em Buffer pelo "+ type + " " +  thread_id + Colors.ANSI_RESET;
	    		sendMessage(session, response);
		    }
	    		    	   	
    
    	
    	}catch(BufferOverflowException e){
    		
    		response =  Colors.ANSI_RED + "Productor " + thread_id  + " tentou colocar item no Buffer cheio" + Colors.ANSI_RESET;
    	}catch(BufferUnderflowException e){
    		response =  Colors.ANSI_RED + "Consumidor " + thread_id + " tentou retirar item do Buffer vazio" + Colors.ANSI_RESET;
    		
    	}catch(InterruptedException e){

    		response =  Colors.ANSI_RED + type +  " " + thread_id + " falhou" + Colors.ANSI_RESET;
    	}catch(IOException e){
    		response = e.getMessage();
    	}finally{
    		
	    	mutex.release();

    	}
    	
        System.out.println( response);
        
        // in unit test session is null
        if(session != null){
	        try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
            	
    }
    
    // in unit test session is null
    public void sendMessage(Session session,String message) throws IOException{
        if(session != null){
    		session.getBasicRemote().sendText(message) ;

        }
    }
    
    
    @OnClose
    public void onClose(Session session) {
        
    }
    
    @OnError
    public void onError(Throwable exception, Session session) {
    }
} 
