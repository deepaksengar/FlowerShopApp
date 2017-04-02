package shoputility;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PurchaseSummary{
	
	final static String currency = "$";
	OrderItem orderReq;
	List<BundleResult> result;
	public static DecimalFormat amountFormat = new DecimalFormat("#.00");
	
	public PurchaseSummary(OrderItem request, List<BundleResult> result){
		this.setOrderReq(request);
		this.setResult(new ArrayList<BundleResult>());
		this.getResult().addAll(result);
	}
	
	public String toString(){
		return this.toString(currency);
	}
	
	public String toString(String currencyCode){
		
		StringBuilder summary = new StringBuilder();
		
		summary.append(getOrderReq().getOrderQuantity()).append(" ").
				append(getOrderReq().getItemCode()).append(" ").
				append(getCost(this.calculateTotalAmount(), currencyCode));
		
		for(BundleResult res : this.getResult()){
			summary.append("\n\t").append(res.getNumberOfBundle()).append(" x ").
					append(res.getBundle().getBundleSize()).append(" ").
					append(getCost(res.getBundle().getBundleCost(), currencyCode));
		}
		
		return summary.toString();
	}
	
	double calculateTotalAmount(){
		double totalCost = 0.0 ;
		for(BundleResult res : this.getResult()){
			totalCost += (res.getNumberOfBundle() * res.getBundle().getBundleCost());
		}
		
		return totalCost;
	}
	
	private static String getCost(double cost, String currencyCode){
		return currencyCode + amountFormat.format(cost);
	}

	public OrderItem getOrderReq() {
		return orderReq;
	}

	private void setOrderReq(OrderItem orderReq) {
		this.orderReq = orderReq;
	}

	public List<BundleResult> getResult() {
		return result;
	}

	private void setResult(List<BundleResult> result) {
		this.result = result;
	}

}
