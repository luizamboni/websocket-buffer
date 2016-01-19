package buffer.consumer;

import junit.framework.TestCase;

public class ConsumerClientEndpointTest extends TestCase{

	public void testOnMessage()
	{
		ConsumerClientEndpoint clientEndpoint = new ConsumerClientEndpoint(1);
		clientEndpoint.getMessage("mensagem de teste");
		
		assertTrue(clientEndpoint.getResponse().matches("mensagem de teste in .* seconds"));

	}
	
	public void testSentMessage(){
		ConsumerClientEndpoint clientEndpoint = new ConsumerClientEndpoint(1);
		assertEquals(clientEndpoint.sentMessage(), "read:Consumer:1");

	}

}
