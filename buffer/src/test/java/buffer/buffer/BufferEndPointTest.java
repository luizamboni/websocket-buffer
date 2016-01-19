package buffer.buffer;

import java.io.IOException;

import javax.websocket.DeploymentException;
import javax.websocket.Session;

import org.glassfish.tyrus.server.Server;

import buffer.server.Buffer;
import buffer.server.BufferEndpoint;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class BufferEndPointTest extends TestCase {

	public void testOnMessageInsert() throws IOException{
		Buffer buffer = new Buffer(2);
		BufferEndpoint bep = new BufferEndpoint();

		bep.onMessage("insert:Productor:1:5",  null);
		assertEquals("Valor 5 adicionado em Buffer pelo Productor 1", bep.getResponse());
		bep.onMessage("insert:Productor:3:5",  null);
		assertEquals("Valor 5 adicionado em Buffer pelo Productor 3", bep.getResponse());
		bep.onMessage("insert:Productor:3:5",  null);
		assertEquals("Productor 3 tentou colocar item no Buffer cheio", bep.getResponse());

	}
	
	public void testOnMessageRead() throws IOException{
		Buffer buffer = new Buffer(1);
		BufferEndpoint bep = new BufferEndpoint();
		bep.onMessage("insert:Productor:1:5",  null);
		bep.onMessage("read:Consumer:1",  null);
		assertEquals("Valor 5 retirado em Buffer pelo Consumer 1", bep.getResponse());
		bep.onMessage("read:Consumer:1",  null);
		assertEquals("Consumidor 1 tentou retirar item do Buffer vazio", bep.getResponse());

		
	}

}
