package myflowershop;
import shoputility.Flower;

public class FlowerShopSeed {
	
	
	public static void initializeFlowerShop(FlowerShop myShop) throws Exception{
		
		Flower roses = new Flower("Roses","R12");
		roses.addBundle(5, 6.99);
		roses.addBundle(10, 12.99);
		
		myShop.addFlowerToShop(roses);
		
		Flower lilies = new Flower("Lilies","L09");
		lilies.addBundle(3, 9.95);
		lilies.addBundle(6, 16.95);
		lilies.addBundle(9, 24.95);
		
		myShop.addFlowerToShop(lilies);
		
		Flower tulip = new Flower("Tulip","T58");
		tulip.addBundle(3, 5.95);
		tulip.addBundle(5, 9.95);
		tulip.addBundle(9, 16.99);
		
		myShop.addFlowerToShop(tulip);
	}

}
