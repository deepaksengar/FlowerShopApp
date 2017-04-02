package shoputility;

public class Bundle {
	
	private int bundleSize;
	private double bundleCost;
	String currencyCode = "$";
	
	private Bundle(int bundleQty, double bundleCost) {
		this.setBundleSize(bundleQty);
		this.setBundleCost(bundleCost);
	}
	
	static Bundle createBundle(int bundleQty, double bundleCost) {
		return new Bundle(bundleQty,bundleCost);
	}
	
	/**
	 * Overriding toString method of Object class, so Bundle object can be displayed as String.
	 */
	@Override
	public String toString(){
		return getBundleSize() + " @ " + currencyCode + getBundleCost() ;
	}
	
	/**
	 * Overriding equals and hasCode method of Object class, so Set of Bundle will have only unique elements
	 * Flower has Set of Bundle, and each bundle for flower should be unique.
	 */
	@Override
	public boolean equals(Object object){
		
		if (this == object){
	         return true;
		}
		
		if (object == null || getClass() != object.getClass()){
	         return false;
		} 
		Bundle bundle = (Bundle) object;
	      
		return (this.getBundleSize() == bundle.getBundleSize() && this.getBundleCost() == bundle.getBundleCost()) ? true : false ;
	}
	
	@Override
	public int hashCode(){
		return Integer.hashCode(getBundleSize()) + Double.hashCode(getBundleCost());
	}
	
	public int getBundleSize(){
		return this.bundleSize;
	}
	
	public double getBundleCost(){
		return this.bundleCost;
		
	}

	private void setBundleSize(int bundleSize) {
		this.bundleSize = bundleSize;
	}

	private void setBundleCost(double bundleCost) {
		this.bundleCost = bundleCost;
	}
}
