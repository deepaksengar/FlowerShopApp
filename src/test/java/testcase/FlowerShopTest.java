package testcase;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import myflowershop.FlowerShop;
import myflowershop.FlowerShopSeed;
import shoputility.Bundle;
import shoputility.BundleResult;
import shoputility.Flower;
import shoputility.Order;
import shoputility.OrderItem;

public class FlowerShopTest {

	/**
	 * this initialize shop with R12, L09, T58 Flowers along with bundle configurations and creates shop catalog
	 * @return
	 */
	private FlowerShop createTestSubject() {
		FlowerShop myFlowerShop = new FlowerShop();
		try{
			FlowerShopSeed.initializeFlowerShop(myFlowerShop);
		} catch(Exception ex){
			System.out.println("Error Occured while putting Products in Shop : " + ex.getMessage());
		}
		return myFlowerShop;
	}
	
	
	/**
	 * testing order for Lilies L09 with Quantity 15
	 * Expected bundle result => 1 x 9 and 1 x 6
	 * @throws Exception
	 */
	@Test
	public void testProcessOrderForLilies() throws Exception {
		FlowerShop testSubject;
		Order order = null;
		Map<OrderItem, List<BundleResult>> result;
		String flowerCode = "L09";

		testSubject = createTestSubject();
		Map<String,Integer> codeToQtyMapForOrder = new HashMap<String,Integer>();
		codeToQtyMapForOrder.put(flowerCode, 15);
		
		order = new Order(codeToQtyMapForOrder);
		OrderItem oi = order.getOrderitems().stream().filter( o-> o.getItemCode().equals(flowerCode)).collect(Collectors.toList()).get(0);
		
		result = new HashMap<OrderItem, List<BundleResult>>(testSubject.processOrder(order));
		
		//result should have bundle values
		assertEquals("Result cannot be empty.",true, (result.get(oi) != null && result.get(oi).size() > 0));
		
		Iterator<BundleResult> iterator = result.get(oi).iterator();
		int[] bundleSize = {6,9};
		while(iterator.hasNext()){
			BundleResult res = iterator.next();
			assertEquals("Result has bundle 9 or 6.",true, Arrays.binarySearch(bundleSize, res.getBundle().getBundleSize()) >= 0);
			
			if(res.getBundle().getBundleSize() == 9){
				assertEquals("Result has bundle with size 9.", true, res.getBundle().getBundleSize() == 9);
				assertEquals("number of bundles with size 9 is 1.", true, res.getNumberOfBundle() == 1);
			}
			
			if(res.getBundle().getBundleSize() == 6){
				assertEquals("Result has bundle with size 6.", true, res.getBundle().getBundleSize() == 6);
				assertEquals("number of bundles with size 6 is 1.", true, res.getNumberOfBundle() == 1);
			}
		}
	}
	
	/**
	 * testing order for Tulip T58 Quantity 13
	 * Expected bundle result => 2 x 5 and 1 x 3
	 * @throws Exception
	 */
	@Test
	public void testProcessOrderTulip() throws Exception {
		FlowerShop testSubject;
		Order order = null;
		Map<OrderItem, List<BundleResult>> result;
		String flowerCode = "T58";

		testSubject = createTestSubject();
		Map<String,Integer> codeToQtyMapForOrder = new HashMap<String,Integer>();
		codeToQtyMapForOrder.put(flowerCode, 13);
		
		// Creating Order for T58
		order = new Order(codeToQtyMapForOrder);
		OrderItem oi = order.getOrderitems().stream().filter( o-> o.getItemCode().equals(flowerCode)).collect(Collectors.toList()).get(0);
		
		//Processing Order and fetching result.
		result = new HashMap<OrderItem, List<BundleResult>>(testSubject.processOrder(order));
		
		assertEquals("Should return result.",true, result.get(oi) != null);
		Iterator<BundleResult> iterator = result.get(oi).iterator();
		int[] bundleSize = {3,5};

		while(iterator.hasNext()){
			BundleResult res = iterator.next();
			assertEquals("Result has bundle of 3/5",true, Arrays.binarySearch(bundleSize, res.getBundle().getBundleSize()) >= 0);
			
			if(res.getBundle().getBundleSize() == 5){
				assertEquals("Number of bundles with size 5 is 2.",true, res.getNumberOfBundle() == 2);
			}
			
			if(res.getBundle().getBundleSize() == 3){
				assertEquals("Number of bundles with size 3 is 1.",true, res.getNumberOfBundle() == 1);
			}
		}
	}
	
	/**
	 * Test for adding flower with bundle config to shop catalog 
	 * @throws Exception
	 */
	@Test
	public void testAddFlowerToShop() throws Exception {
		FlowerShop testSubject;
		Flower flower = null;

		testSubject = createTestSubject();
		
		flower = new Flower("Roses","R12");
		flower.addBundle(5, 6.99);
		flower.addBundle(10, 12.99);
		
		try{
			testSubject.addFlowerToShop(flower);
		} catch (Exception ex){
			fail("Valid Flower was not added.");
		}
		Flower result = testSubject.getFlowerShopCatalogue().get("R12");
		assertEquals("Flower has been added to catalogue",true, (result != null && "R12".equalsIgnoreCase(result.getFlowerCode()))); 
	}
	
	/**
	 * Testing for Available bundle for Roses : R12
	 * @throws Exception
	 */
	@Test
	public void testGetBundle() throws Exception {
		FlowerShop testSubject;
		String flowerCode = "R12";
		Set<Bundle> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getBundle(flowerCode);
		assertEquals("Bundle should exist for Roses - R12", true, result.size() > 0);
	}
	
	/**
	 * Testing for Available Flower with flower-code : L09
	 * @throws Exception
	 */
	@Test
	public void testGetFlower() throws Exception {
		FlowerShop testSubject;
		String flowerCode = "L09";
		Flower result;

		testSubject = createTestSubject();
		result = testSubject.getFlower(flowerCode);
		assertEquals("Flower should exist for code - L09", true, result != null);
		assertEquals("Flower name should be for code - L09", true, "Lilies".equalsIgnoreCase(result.getFlowerName()));
	}
	
	/**
	 * test to check Flower code L09 exist in shop catalog
	 * @throws Exception
	 */
	@Test
	public void testGetFlowerShopCatalogue() throws Exception {
		FlowerShop testSubject;
		Map<String, Flower> result;

		testSubject = createTestSubject();
		result = testSubject.getFlowerShopCatalogue();
		assertEquals("Lilies L09 should exist in flower shop catalog, with Bundle size > 0.", true, 
				(result.containsKey("L09") 
					&& "Lilies".equalsIgnoreCase(result.get("L09").getFlowerName()) 
					&& result.get("L09").getFlowerBundle().size() > 0));

	}

}
