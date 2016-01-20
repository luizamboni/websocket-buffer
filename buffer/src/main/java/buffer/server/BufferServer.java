package buffer.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.glassfish.tyrus.server.Server;

import buffer.server.BufferEndpoint;


public class BufferServer {
	
	private Integer Port;
	private Buffer Buffer;
	
	public BufferServer(Integer port, Integer bufferSize ){
		this.Port = port;
		this.Buffer = new Buffer(bufferSize);
		
	}
	

	
	public void start(){
		
		
        Server server = new Server("localhost", Port, "", BufferEndpoint.class );
       
        
        try {
        	server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please press a key to stop the server.");
            reader.readLine();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
        	server.stop();
        }
	}
	
	
}
