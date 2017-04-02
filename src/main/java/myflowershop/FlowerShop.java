package myflowershop;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import errorutility.BundleNotFoundException;
import errorutility.FlowerNotFoundException;
import shoputility.Bundle;
import shoputility.BundleHelper;
import shoputility.BundleResult;
import shoputility.Flower;
import shoputility.IShop;
import shoputility.Order;
import shoputility.OrderItem;
import shoputility.PurchaseSummary;

public class FlowerShop implements IShop {
	
	public static final String currencyCode = "$";
	public static String logger;
	Map<String, Flower> flowerCodeToFlower;
	
	public FlowerShop(){
		flowerCodeToFlower = new HashMap<String, Flower>();
	}
	
	public Map<String, Flower> getFlowerShopCatalogue(){
		return this.flowerCodeToFlower;
	}
	
	public void addFlowerToShop(Flower flower) throws RuntimeException {
		if(isValid(flower)){
			
			if(!isValid(flower.getFlowerBundle())) {
				throw new RuntimeException("Error - Cannot add Flowers without bundle to Shop Catalogue."); 
			}
			
			getFlowerShopCatalogue().put(flower.getFlowerCode(), flower);
		
		} else {
			throw new NullPointerException("Error - Cannot Add an empty flower to shop catalogue.");
		}
	}
	
	public Flower getFlower(String flowerCode) throws Exception {
		Flower flower;
		
		if(!isValid(flowerCode)){
			throw new FlowerNotFoundException("Flower Code is not valid");
		} else {
			flowerCode = flowerCode.toUpperCase();
			flower = flowerCodeToFlower.containsKey(flowerCode) ? flowerCodeToFlower.get(flowerCode) : null;
		}
		
		if(!isValid(flower)){
			throw new FlowerNotFoundException("Flower doesn't exist in Shop Catalogue.",flowerCode);
		}
		
		return flower;
	}
	
	public Set<Bundle> getBundle(String flowerCode) throws Exception {
		flowerCode = flowerCode.toUpperCase();
		Flower flower = getFlower(flowerCode);
		Set<Bundle> bundles = flower.getFlowerBundle();
		
		if(!isValid(bundles)){
			throw new BundleNotFoundException("Bundle doesn't exist for Flower Code : " +flowerCode);
		}	
		
		return bundles;
	}
	
	public Map<OrderItem, List<BundleResult>> processOrder(Order order){
		Map<OrderItem, List<BundleResult>> orderItemResult = new HashMap<OrderItem, List<BundleResult>>();
		StringBuilder errors = new StringBuilder();
		int errorCount = 0;
		logger = "";
		
		for(OrderItem oi: order.getOrderItems()){

			List<BundleResult> oiResult;
			try{
				oiResult = BundleHelper.findOptimalBundleSolution(oi.getOrderQuantity(), getBundle(oi.getItemCode()));
			} catch(Exception ex){
				oiResult = null;
				errorCount++;
				errors.append("Error (" + errorCount + ") - " + ex.getMessage() + " \n");
			}
			
			logger  = errors.toString();
			orderItemResult.put(oi,oiResult);
		}
		return orderItemResult;
	}
	
	public PurchaseSummary generatePurchaseSummary(OrderItem oi, List<BundleResult> bundleRes){
		return new PurchaseSummary(oi, bundleRes);
	}

	public void printCatalogue() {
		Map<String, Flower> catalogue = this.getFlowerShopCatalogue();
		catalogue.forEach((key,value) -> System.out.println(value.toString()));
	}
}
