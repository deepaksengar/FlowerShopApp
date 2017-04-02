package shoputility;


import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Order {
	
	private Set<OrderItem> orderItems;
	
	/**
	 *  generates order items
	 * @param itemCodeToQuantity
	 */
	
	public Order(Map<String,Integer> itemCodeToQuantity){
		this.setOrderItems(new HashSet<OrderItem>());
		for(String key : itemCodeToQuantity.keySet()){
			this.getOrderItems().add(new OrderItem(key, itemCodeToQuantity.get(key)));
		}
	}
	
	public Set<OrderItem> getOrderitems(){
		return this.getOrderItems();
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
}
