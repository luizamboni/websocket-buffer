package buffer.server;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.util.ArrayList;

public class Buffer {

	private Integer Size;
	private static ArrayList<Integer> Contents; 
	private static Buffer Instance; 
	private static Boolean Locked;
	
	public Buffer(Integer size){
		this.Size = size;
		this.Contents = new ArrayList<Integer>();
		this.Instance = this;
	}
	
	public int getSize(){
		
		return Contents.size();
	}
	
	
	public void insert(Integer value){
		if(Contents.size() < Size){
			Contents.add(value);
		}else{
			// limite
			throw new BufferOverflowException();
		}
	}
	
	public Integer read(){
		if (!Contents.isEmpty()){
			int index= Contents.size() - 1;
			Integer out = Contents.get(index);
			Contents.remove(index);
			return out;
		}else{
			// tem menos do que 0
			throw new BufferUnderflowException();
		}
		
	}
	
	public ArrayList<Integer> GetContent(){
		return  Contents;
	}
	
	
	public static Buffer getInstance(){
		return Instance;
		
	}
}
