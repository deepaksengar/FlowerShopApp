package errorutility;


public class BundleNotFoundException extends RuntimeException {
	
	
	/**
	 * Default Serial Id.
	 */
	private static final long serialVersionUID = 1L;

	public BundleNotFoundException(String message){
		super(message);
	}
	
	public BundleNotFoundException(){
		this("Bundle not found.");
	}
	
	public BundleNotFoundException(String message, String code){
		this(new StringBuilder().append("Bundle not for Flower Code : ").append(code).append(" .").append(message).toString());
	}
}
