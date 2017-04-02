package shoputility;


import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IShop {

	default double calculateCost(int units, double price){
		return units*price;
	}
	
	default <E> boolean isValid(E item){
		
		if(item == null)
			return false;
		
		if(item instanceof String)
			return !("".equals(item));
		
		if(item instanceof Collection)
			return !(((Collection<?>) item).isEmpty()) ;
		
		if(item instanceof Flower)
			return !(((Flower) item).getFlowerCode().isEmpty()) ;
		
		return false;
	}
	
	public Map<OrderItem, List<BundleResult>> processOrder(Order order);
	
	public PurchaseSummary generatePurchaseSummary(OrderItem orderitem, List<BundleResult> bundleResult);
	
//	public <E> List<E> getShopCatalogue();
	
	//List<Bundle> getBundle(String id);

}
