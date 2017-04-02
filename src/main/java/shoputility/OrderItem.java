package shoputility;

public class OrderItem {
	
	private String itemCode;
	private int orderQuantity;
	
	public OrderItem(String itemCode, int orderQuantity){
		this.setItemCode(itemCode);
		this.setOrderQuantity(orderQuantity);
	}
	
	/**
	 * Overriding equals and hasCode method of Object class, so order-item can be used as Map Key to process PurchaseSummary
	 */
	@Override
	public boolean equals(Object object){
		
		if (this == object){
	         return true;
		}
		
		if (object == null || getClass() != object.getClass()){
	         return false;
		} 
		OrderItem oi = (OrderItem) object;
	      
		return (this.getOrderQuantity() == oi.getOrderQuantity() && this.getItemCode() == oi.getItemCode()) ? true : false ;
	}
	
	@Override
	public int hashCode(){
		return Integer.hashCode(getOrderQuantity()) + getItemCode().hashCode();
		
	}
	
	public int getOrderQuantity(){
		return this.orderQuantity;
	}
	
	public String getItemCode(){
		return this.itemCode;
	}

	private void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	private void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
}
