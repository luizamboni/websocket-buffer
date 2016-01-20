package buffer.consumer;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

public class ConsumerClientEndpointTest extends TestCase{

	public void testOnMessage()
	{
		ConsumerClientEndpoint clientEndpoint = new ConsumerClientEndpoint(1, new CountDownLatch(1));
		clientEndpoint.getMessage("mensagem de teste");
		
		assertTrue(clientEndpoint.getResponse().matches("mensagem de teste in .* seconds"));

	}
	
	public void testSentMessage(){
		ConsumerClientEndpoint clientEndpoint = new ConsumerClientEndpoint(1, new CountDownLatch(1));
		assertEquals(clientEndpoint.sentMessage(), "Consumer:1:read");

	}

}
