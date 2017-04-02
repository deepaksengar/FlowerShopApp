package shoputility;


import java.util.HashSet;
import java.util.Set;

import errorutility.FlowerNotFoundException;

public class Flower {
	String flowerName;
	String flowerCode;
	Set<Bundle> flowerBundle;

	public Flower(String name, String code){
		this.setFlowerName(name);
		this.setFlowerCode(code.toUpperCase());
		this.setFlowerBundle(new HashSet<Bundle>());
	}
	
	public Flower(String name, String code, int quantity, double cost){
		(new Flower(name, code)).getFlowerBundle().add(Bundle.createBundle(quantity,cost));
	}
	
	public void addBundle(int quantity, double cost) throws Exception {
		
		if(this == null || this.flowerCode == null || "".equals(this.flowerCode )){
			throw new FlowerNotFoundException();
		}

		Bundle bundle = Bundle.createBundle(quantity,cost);
		this.getFlowerBundle().add(bundle);
	}
	
	public String toString(){
		return this.flowerName + " " + this.flowerCode + " "  + toString(this.flowerBundle); 
	}
	
	public String toString(Set<Bundle> list){
		StringBuilder sb = new StringBuilder();
		for (Bundle s : list)
		{
		    sb.append("\n\t").append(s.toString());
		}
		return sb.toString();
		
	}
	
	public Set<Bundle> getFlowerBundle() {
		return flowerBundle;
	}

	private void setFlowerBundle(Set<Bundle> flowerBundle) {
		this.flowerBundle = flowerBundle;
	}

	public String getFlowerName() {
		return flowerName;
	}

	private void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}

	public String getFlowerCode() {
		return flowerCode;
	}

	private void setFlowerCode(String flowerCode) {
		this.flowerCode = flowerCode;
	}
}
