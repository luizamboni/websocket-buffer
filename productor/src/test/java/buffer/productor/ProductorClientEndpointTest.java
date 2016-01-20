package buffer.productor;

import java.util.concurrent.CountDownLatch;

import junit.framework.TestCase;

public class ProductorClientEndpointTest extends TestCase{


	public void testOnMessage()
	{
		ProductorClientEndpoint clientEndpoint = new ProductorClientEndpoint(new CountDownLatch(1));
		clientEndpoint.getMessage("mensagem de teste");
		
		assertTrue(clientEndpoint.getResponse().matches("mensagem de teste in .* seconds"));

	}
	
	public void testSetMessage(){
		ProductorClientEndpoint clientEndpoint = new ProductorClientEndpoint(new CountDownLatch(1));
		clientEndpoint.setMessage(2, 4);
		assertEquals(clientEndpoint.sentMessage(),"Productor:2:insert:4");

	}



}
