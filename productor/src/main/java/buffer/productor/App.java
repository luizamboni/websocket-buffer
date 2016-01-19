package buffer.productor;

import java.util.Arrays;
import java.util.List;

public class App 
{
	static Integer threads;
	static String host;
	static Integer port;
	static List<String> options;
	
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
    	
        // get number of Threads 
	 	threads = getIntegerOption("-t");
	 	
        // get number of port 
	 	port = getIntegerOption("-p");
	 	
        // get host
	 	host = getStringOption("-h");
	 	
		System.out.println( "Init Productors" );
		new ProductorsFactory(threads,host,port).run();
    }
}
