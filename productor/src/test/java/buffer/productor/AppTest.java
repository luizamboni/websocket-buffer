package buffer.productor;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    public AppTest( String testName )
    {
        super( testName );
    }


    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testAppParams()
    {
    	String[] args = {"-p","8080", "-t", "3","-h","localhost"} ; 
    	App.setOptions(args);

    	
    	assertTrue("options", App.getIntegerOption("-p") ==  8080);
    	assertTrue("options", App.getIntegerOption("-t") ==  3);
    	assertTrue("options", App.getStringOption("-h") ==  "localhost");

    }
}
