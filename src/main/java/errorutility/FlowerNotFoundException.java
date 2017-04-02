package errorutility;


public class FlowerNotFoundException extends RuntimeException {
	
	/**
	 * Default Serial Id. Exception Class implements Serializable interface.
	 */
	private static final long serialVersionUID = 1L;

	public FlowerNotFoundException(String message){
		super(message);
	}
	
	public FlowerNotFoundException(){
		this("Flower does not exist.");
	}
	
	public FlowerNotFoundException(String message, String itemCode){
		this(new StringBuilder().append("Flower does not exist for Flower Code : ").append(itemCode).append(" .").append(message).toString()); 
	}

}
