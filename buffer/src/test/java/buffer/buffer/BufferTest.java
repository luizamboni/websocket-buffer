package buffer.buffer;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

import buffer.server.Buffer;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class BufferTest extends TestCase{

	// retorn a subject Class of Tests
	static private Buffer subject(int size ){
		return new Buffer(size);
	}
	
	static private Buffer subject(){
		return new Buffer(5);
	}
	
    public BufferTest( String testName )
    {
        super( testName );
    }
    
    public void testgetSize(){
    	Buffer buffer = subject();
    	
    	assertTrue(".getSize() == 0 ",  (buffer.getSize() == 0)  );
    	
    	buffer.insert(1); buffer.insert(1);
    	buffer.insert(1); buffer.insert(1);
    	buffer.insert(1);

     	assertTrue(".getSize() == 5 ",  (buffer.getSize() == 5)  );
    
     	buffer.read(); buffer.read();

     	assertTrue(".getSize() == 3",  (buffer.getSize() == 3)  );
    	
    }
    
    
    public void testBufferOverflow(){
    	Buffer buffer = subject(1);
    	
    	try{
    		buffer.insert(1); buffer.insert(1);

    	}catch(BufferOverflowException e){
    		
    		Boolean isBufferOverflowException = (e.getClass().getSimpleName().equals( "BufferOverflowException") );
    		assertTrue("isBufferOverflowException is eq \"BufferOverflowException\"", isBufferOverflowException );

    	}
    	
    }
    
    public void testBufferUnderflow(){
    	
    	Buffer buffer = subject(2);

     	
    	try{
    		buffer.read();
 
    	}catch(BufferUnderflowException e){
    	  Boolean isBufferUnderflowException = (e.getClass().getSimpleName().equals( "BufferUnderflowException") );
          
    	  assertTrue("isBufferUnderflowException is eq \"BufferUnderflowException\"", isBufferUnderflowException );

    	}
     	
    }
        


	

}
