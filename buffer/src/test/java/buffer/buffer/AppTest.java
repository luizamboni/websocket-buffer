package buffer.buffer;

import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import buffer.server.App;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase
{

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
    	String[] args = {"-p","8080", "-s", "3"} ; 
    	App.setOptions(args);

    	
    	assertTrue("options", App.getIntegerOption("-p") ==  8080);
    	assertTrue("options", App.getIntegerOption("-s") ==  3);

    }
}
