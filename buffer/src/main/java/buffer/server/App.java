package buffer.server;

import java.util.Arrays;
import java.util.List;


public class App 
{
	static Integer size; 
	static Integer port;
	static public List<String> options;
	
	public static String getStringOption(String option){
		Integer index = options.indexOf(option);
	 	if (index != -1 ){
	 		return options.get(index + 1);
		}else{
			return null;
		}
	 	
		
	}
	
	public static Integer getIntegerOption(String option){
		Integer index = options.indexOf(option);
		
	 	if (index != -1 ){
	 		return Integer.valueOf( options.get(index + 1));
		}else{
			return null;
		}
		
	}	
	
	public static void  setOptions(String[] opts){ 
    	options = Arrays.asList(opts);

	}

	
    public static void main( String[] args )
    {
    	
    	setOptions(args);
    	
    	options = Arrays.asList(args);

        // get number of port 
	 	port = getIntegerOption("-p");
	 	
        // get size
	 	size = getIntegerOption("-s");
	 	
    	// deixo aqui para ver se está rodando
         System.out.println( "initialized" );
        

         Init();
    }
    
    public static void Init(){
        BufferServer server = new BufferServer(port, size);
        server.start();
    }
    
}
