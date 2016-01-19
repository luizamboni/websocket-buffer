package buffer.server;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.concurrent.Semaphore;

import javax.websocket.*;
import javax.websocket.server.*;

@ServerEndpoint(value = "/")
public class BufferEndpoint { 

	private Buffer Buffer;
    static Semaphore mutex;
    private String response;
    
    public String getResponse(){
    	return response;
    }
    
	public BufferEndpoint(){
		this.Buffer =  Buffer.getInstance();
		this.mutex =  new Semaphore(1);
	}
	
	
    @OnOpen
    public void onOpen() {

    }
    
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
    	


        /* formato de mensagem insert comand:type:id:value
         * poderia sem num json também, mas no caso presente nâo vi 
         * necessidade e preferi uma sintaxa proxima as chaves do Redis
         * 
    	 *	Exemplos
    	 *  
    	 * 		insert:Productor:5:40
    	 *		read:Consummer:5
    	 */
    	
    	String command = message.split(":" )[0];
    	String type = message.split(":" )[1];	
    	String thread_id = message.split(":" )[2];	
    	Integer value;
    	
    	
    	String humanizedAction;
    	try{
    		
        	mutex.acquire();
        	
	    	if(command.equals("insert")){ 
	    		
	        	value = Integer.valueOf( message.split(":" )[3] );	
	            humanizedAction = "adicionado";
	            Buffer.insert( value);
		    }else if(command.equals("read")){
		    	humanizedAction = "retirado";
		    	value  = Buffer.read();
		    	
		    }else{
		    	// TODO tratar como erro
		    	humanizedAction = "";
		    	value = null;
		    }
	    		    	
	    	if(command.equals("insert")){
	    		response =   "Valor "+ value.toString() + " " + humanizedAction + " em Buffer pelo "+ type + " " +  thread_id;
	    		
	    	}else if(command.equals("read")){
	    		response =   "Valor "+ value.toString() + " " + humanizedAction + " em Buffer pelo "+ type + " " +  thread_id;

	    	}else{
	    		response =  "nenhuma operação identificada";
	    	} 	
    	
    	
    	if(session != null && response != "nenhuma operação identificada"){

    		session.getBasicRemote().sendText(response);
    	}
    	}catch(BufferOverflowException e){
    		
    		response =  "Productor " + thread_id  + " tentou colocar item no Buffer cheio";
    	}catch(BufferUnderflowException e){
    		response =  "Consumidor " + thread_id + " tentou retirar item do Buffer vazio";
    		
    	}catch(InterruptedException e){

    		response =  type +  " " + thread_id +" falhou";
    	}finally{
    		
	    	mutex.release();

    	}
    	
        System.out.println( response);
        
        if(session != null){
	        try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
            	
    }
    
    @OnClose
    public void onClose(Session session) {
        
    }
    
    @OnError
    public void onError(Throwable exception, Session session) {
    }
} 
