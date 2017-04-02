package shoputility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BundleHelper {

	public static List<BundleResult> findOptimalBundleSolution(int quantity, Set<Bundle> bundles) throws Exception{
		
		// Creating a Map of bundleSize to Bundle
		Map<Integer, Bundle> qtyToBundleMap = 
				(Map<Integer, Bundle>) bundles.stream().collect(Collectors.toMap(bundle -> bundle.getBundleSize(), bundle -> bundle));
		
		return getBundleResult(quantity, bundles, qtyToBundleMap);
	}

	private static List<BundleResult> getBundleResult(int quantity, Set<Bundle> bundles, Map<Integer, Bundle> qtyToBundleMap) {
		
		// Discarding all bundles where OrderQuantity < bundleSize
		List<Integer> refinedBundleQtyList = 
				 bundles.stream().filter(bundle -> 
				 	bundle.getBundleSize() <= quantity).map(bundle -> bundle.getBundleSize()).collect(Collectors.toList());
		
		Collections.sort(refinedBundleQtyList);
		
		List<Integer> optimalSolution = getMinNumberOfBundles(refinedBundleQtyList, quantity);
		
		return createBundleResultList(optimalSolution, qtyToBundleMap);
	}

	//Main algorithm to find minimum number of bundles required to serve order, bundleSizeList is sorted
	private static List<Integer> getMinNumberOfBundles(List<Integer> bundleSizeList, int orderQuantity) {
        
		Map<Integer,Integer> orderQuantityToMinCount = new HashMap<Integer,Integer>();
        Map<Integer,ArrayList<Integer>> orderQtyToBundleSizeList = new HashMap<Integer,ArrayList<Integer>>();
        
        //Initializing : For 0 quantity min no of selection available is 0
        orderQuantityToMinCount.put(0, 0);
        
        //for OrderQty 0, budleSizeList will be empty
        orderQtyToBundleSizeList.put(0, new ArrayList<Integer>());
        
        int qtyCount = 1;
        
        //Finding Solution by Bottom up approach. 
        //Finding bundles to serve  from quantity=1 to quantity=orderQuantity
        while(qtyCount<=orderQuantity){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            boolean notPossible = true;
            
            orderQtyToBundleSizeList.put(qtyCount, new ArrayList<Integer>());
            
            for(int index=0; index < bundleSizeList.size(); index++){
            	
                if(qtyCount >= bundleSizeList.get(index)){
                    if(orderQuantityToMinCount.get(qtyCount-bundleSizeList.get(index)) != -1){
                        int temp = orderQuantityToMinCount.get(qtyCount-bundleSizeList.get(index)) + 1;
                        
                        if(temp < min) {
                        	min = temp;
                        	minIndex = index;
                        }
                        notPossible = false;
                    }
                }
            }
           
            if(notPossible){
            	orderQuantityToMinCount.put(qtyCount,-1);
            } else{
                orderQuantityToMinCount.put(qtyCount,min);
                orderQtyToBundleSizeList.get(qtyCount).addAll(orderQtyToBundleSizeList.get(qtyCount-bundleSizeList.get(minIndex)));
                orderQtyToBundleSizeList.get(qtyCount).add(bundleSizeList.get(minIndex));
            }
            qtyCount++;
        }

        return orderQtyToBundleSizeList.get(orderQuantity);
    }
	
	private static List<BundleResult> createBundleResultList(List<Integer> optimalSolution, Map<Integer, Bundle> qtyToBundleMap) {
		List<BundleResult> bundleResultList = new ArrayList<BundleResult>();
		
		if(optimalSolution.size() > 0) {
			
			//Creating a map of BundleSize to Its count to prepare bundle result
			Map<Integer, Integer> bundleQtyToCount = (Map<Integer, Integer>) optimalSolution.stream().collect(Collectors.groupingBy( i -> i, Collectors.summingInt(e -> 1)));
			
			for(Integer bundleSize : bundleQtyToCount.keySet()){
				int numberOfBundle = bundleQtyToCount.get(bundleSize) ;
				
				Bundle bundle = qtyToBundleMap.get(bundleSize);
				
				BundleResult result = new BundleResult(numberOfBundle, bundle);
				bundleResultList.add(result);
			}
		}
		
		return bundleResultList;
	}
	
}
