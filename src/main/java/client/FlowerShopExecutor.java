package client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import flowershop.FlowerShop;
import flowershop.FlowerShopOneSeed;
import shoputility.Order;

/**
 * @author deepaksinghsengar1@gmail.com
 * This client class takes order for flower-shop and asks flower-shop to process it. 
 */
public class FlowerShopExecutor {
	

	public static void main(String[] args) throws IOException {
		
		FlowerShop myFlowerShop = initializeMyFlowerShop();
		
		System.out.println("Sample Order (Quantity Code): ");
		
		Map<String,Integer> codeToQtyMapForOrder = new HashMap<String,Integer>();
		codeToQtyMapForOrder.put("R12", 10);
		codeToQtyMapForOrder.put("L09", 15);
		codeToQtyMapForOrder.put("T58", 13);
		
		codeToQtyMapForOrder.forEach((key,value) -> System.out.println(value + " " + key));
		
		//executing sample order...
		executeOrder(myFlowerShop, codeToQtyMapForOrder);
		
		
		// Additional Order Execution from eclipse console / command line
		getNewOrderFromUser(myFlowerShop, codeToQtyMapForOrder);
		
		System.out.println("\n\nThank you for Shopping.\n\nExisting...");
	}

	static FlowerShop initializeMyFlowerShop() {
		System.out.println("Initializing FlowerShop.");
		FlowerShop myFlowerShop = new FlowerShop();
		
		try{
			FlowerShopOneSeed.initializeFlowerShop(myFlowerShop);	//Initializing myFlowerShop with predefined catalog. 
		} catch(Exception ex){
			System.out.println("Error Occured while putting Products in Shop : " + ex.getMessage());
		}
		
		myFlowerShop.printCatalogue();
		return myFlowerShop;
	}
	
	static void getNewOrderFromUser(FlowerShop myFlowerShop, Map<String, Integer> codeToQtyMapForOrder)
			throws IOException {
		Scanner scan = new Scanner(System.in);
		System.out.println("\nDo you want to place order? enter Y or Yes, else press enter to quit.\n");
		String choice = scan.nextLine();
		
		if(!"".equals(choice) && ("y".equalsIgnoreCase(choice) || "yes".equalsIgnoreCase(choice))){
			System.out.println("Enter your order details as \"Quantity\" \"FlowerCode\" . \nType \"Done\" when order finished.");
			getInputForOrder(scan,codeToQtyMapForOrder);
			executeOrder(myFlowerShop, codeToQtyMapForOrder);
		} else {
			scan.close();
		}
	}
	
	// *functions to execute order request.
	private static void executeOrder(FlowerShop myFlowerShop, Map<String, Integer> codeToQtyMapForOrder) {
		
		if(codeToQtyMapForOrder.keySet().size() == 0){
			System.out.println("Invalid Format/No correct flower details found in Input...");
			return;
		}
		
		System.out.println("Processing Order...");
		Order order = new Order(codeToQtyMapForOrder);
		
		/**
		 * executeOrder(Order) function executes order for flowerShop and generates PurchaseSummary for each order item..
		 */
		
		String result = myFlowerShop.executeOrder(order);
		
		System.out.println(result);
	}

	// Input Reader from console to execute Order.
	private static void getInputForOrder(Scanner scan,Map<String, Integer> codeToQtyMapForOrder) throws IOException {
		
		String input ;
		codeToQtyMapForOrder.clear();
		
		while((scan.hasNext() && (input = scan.nextLine()) != null && !"done".equalsIgnoreCase(input))){
			if(input.split(" ").length == 2){
				String itemCode = input.split(" ")[1].toUpperCase();
				int quantity = 0;
				try{
					quantity = Integer.valueOf(input.split(" ")[0]);
					
					if(codeToQtyMapForOrder.containsKey(itemCode)){
						codeToQtyMapForOrder.put(itemCode,codeToQtyMapForOrder.get(itemCode) + quantity);
					} else {
						codeToQtyMapForOrder.put(itemCode, quantity);
					}
					
				} catch(Exception ex){
					System.out.println("Wrong Input. Enter quantity first, then Flower Code"); 
				}
				
			} else if(!"done".equalsIgnoreCase(input))	{
				System.out.println("Invalid input : " + (input.length() == 0 ? " empty line" : input));
				break;
			}
		}
		scan.close();
	}

}
