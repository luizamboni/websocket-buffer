package buffer.buffer;

import java.io.IOException;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.server.Server;

import buffer.server.Buffer;
import buffer.server.BufferEndpoint;
import buffer.server.Colors;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class BufferEndPointTest extends TestCase {

	public void testOnMessageInsert() throws IOException{
		Buffer buffer = new Buffer(2);
		BufferEndpoint bep = new BufferEndpoint();

		bep.onMessage("Productor:1:insert:5",  null);
		assertEquals(Colors.ANSI_GREEN + "Valor 5 adicionado em Buffer pelo Productor 1" + Colors.ANSI_RESET, bep.getResponse());
		bep.onMessage("Productor:3:insert:5",  null);
		assertEquals(Colors.ANSI_GREEN + "Valor 5 adicionado em Buffer pelo Productor 3" + Colors.ANSI_RESET, bep.getResponse());
		bep.onMessage("Productor:3:insert:5",  null);
		assertEquals(Colors.ANSI_RED + "Productor 3 tentou colocar item no Buffer cheio" +  Colors.ANSI_RESET, bep.getResponse());

	}
	
	public void testOnMessageRead() throws IOException{
		Buffer buffer = new Buffer(1);
		BufferEndpoint bep = new BufferEndpoint();
		bep.onMessage("Productor:1:insert:5",  null);
		bep.onMessage("Consumer:1:read",  null);
		assertEquals(Colors.ANSI_GREEN + "Valor 5 retirado em Buffer pelo Consumer 1" + Colors.ANSI_RESET, bep.getResponse());
		bep.onMessage("Consumer:1:read",  null);
		assertEquals(Colors.ANSI_RED  + "Consumidor 1 tentou retirar item do Buffer vazio" + Colors.ANSI_RESET, bep.getResponse());

		
	}

}
